package com.youkke.info.web;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.youkke.info.domain.Info;
import com.youkke.info.service.InfoService;
import com.youkke.utils.MyPage;

@Controller
public class IndexController extends BaseController {

	@Autowired
	private InfoService infoService;

	@GetMapping("/")
	public String index(Model model,
			@RequestParam(value = "pagesize", defaultValue = "20", required = false) Integer pagesize) {
		MyPage<Info> ps;
		if (StringUtils.isNotBlank(sessuserid)) {
			ps = infoService.findAll(sessuserid, null, null, null, null, null, syscates, cmcates, tags, keyword,
					orderfield, order, pagesize, page);
			Map<Integer, List<Info>> list = ps.split(2);
			model.addAttribute("infos", ps);
		} else {
			// 未登录时，仅显示人工审核推荐的信息；用户登录后，还汇总显示用户加入的社群最新的信息。
			ps = infoService.findHomeAll(sessuserid, syscates, cmcates, keyword, orderfield, order, pagesize, page);
			model.addAttribute("infos", ps);
		}
		return "index";
	}

}
