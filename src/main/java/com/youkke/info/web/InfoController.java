package com.youkke.info.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.youkke.info.domain.Comment;
import com.youkke.info.service.InfoService;
import com.youkke.utils.JsonResult;
import com.youkke.utils.MyPage;

@Controller
public class InfoController extends BaseController {
	@Autowired
	private InfoService infoService;

	@GetMapping("/info/input")
	public String input(Model model) {
		return "input";
	}

	@GetMapping("/info/input/markdown")
	public String markdown(Model model) {
		return "markdown";
	}

	@GetMapping("/info/input/upmarkdown/{id}")
	public String upmarkdown(@PathVariable String id, Model model) {
		model.addAttribute("info", infoService.findById(sessuserid, id));
		return "update_markdown";
	}

	@PostMapping("/info")
	@ResponseBody
	public String create(@Valid InfoCreateForm infoCreate, BindingResult result) {
		List<JsonResult> arr = new ArrayList<JsonResult>();

		if (result.hasErrors()) {
			// result.rejectValue("account", "aaaaaa", "bbbbbbbbbbbbbb");
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				FieldError fe = (FieldError) error;
				System.err.println(fe.getField());
				JsonResult jr = new JsonResult();
				jr.setField(fe.getField());
				jr.setMessage(error.getDefaultMessage());
				arr.add(jr);
			}
		} else {
			infoService.save(sessuserid, infoCreate);
			JsonResult jr = new JsonResult();
			jr.setMessage("success");
			jr.setContent(infoCreate.getContent());
			if (infoCreate.getTitle() == null) {
				jr.setTitle("");
			} else {
				jr.setTitle(infoCreate.getTitle());
			}
			arr.add(jr);
		}

		return JSON.toJSONString(arr);
	}

	@PostMapping("/info/{id}")
	@ResponseBody
	public Map<String, Object> update(@PathVariable String id, @Valid InfoUpdateForm infoUpdate) {
		Map<String, Object> map = new HashMap<String, Object>();
		infoService.update(sessuserid, id, infoUpdate);
		map.put("result", "success");
		return map;
	}

	@PostMapping("/info/{id}/delete")
	@ResponseBody
	public Map<String, Object> delete(@PathVariable String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		infoService.delete(sessuserid, id);
		map.put("result", "success");
		return map;
	}

	@GetMapping("/info/{id}")
	public String findOne(@PathVariable String id, Model model) {
		model.addAttribute("info", infoService.findById(sessuserid, id));
		return "info";
	}

	@GetMapping("/info/{id}/comments")
	public String loadByInfo(Model model, @PathVariable String id) {
		model.addAttribute("comments", infoService.findCommentById(id, keyword, pagesize, page));
		return "info_comments";
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable String id, Model model) {
		model.addAttribute("info", infoService.findById(sessuserid, id));
		System.out.println(id);
		return "update";
	}

	@PostMapping("/info/{id}/comment")
	@ResponseBody
	public void saveComment(@PathVariable String id, @Valid Comment comment) {
		infoService.saveComment(sessuserid, id, comment.getContent());
		// return JSON.toJSONString("ok");
	}

	@GetMapping("/cm_load/{id}/{pagesize}")
	public String load(Model model, @PathVariable String id, @PathVariable Integer pagesize) {
		MyPage<Comment> ps;
		MyPage mypage = new MyPage();
		model.addAttribute("ps", mypage);
		ps = infoService.findCommentById(id, keyword, pagesize, page);
		Map<Integer, List<Comment>> list = ps.split(2);
		model.addAttribute("info", ps);
		System.out.println(ps.getTotalcount());
		return "cm_load";
	}
}
