package com.youkke.info;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.youkke.info.service.InfoService;
import com.youkke.info.utils.Constant;
import com.youkke.info.web.InfoCreateForm;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = YkinfoApplication.class)
public class InfoServiceTest {
	@Autowired
	private InfoService infoService;
	
	@Before
	public void init() {
		InfoCreateForm infoCreate = new InfoCreateForm();
		infoCreate.setScope(Constant.PUBLIC);
		String markdown = "### 主要特性"
				+ "  - 支持“标准”Markdown / CommonMark和Github风格的语法，也可变身为代码编辑器；"
				+ "	 - 支持实时预览、图片（跨域）上传、预格式文本/代码/表格插入、代码折叠、搜索替换、只读模式、自定义样式主题和多语言语法高亮等功能；";
		infoCreate.setMarkdown(markdown);
		infoCreate.setContent("test");
		infoService.save("0042dd84ff4d4246a0e3d06095392a86", infoCreate);
	}
	
	@Test
	public void find() {
		InfoCreateForm infoCreate = new InfoCreateForm();
		infoCreate.setScope(Constant.PUBLIC);
		String markdown = "### 主要特性"
				+ "  - 支持“标准”Markdown / CommonMark和Github风格的语法，也可变身为代码编辑器；"
				+ "	 - 支持实时预览、图片（跨域）上传、预格式文本/代码/表格插入、代码折叠、搜索替换、只读模式、自定义样式主题和多语言语法高亮等功能；";
		infoCreate.setMarkdown(markdown);
		infoCreate.setContent("test");
		infoService.save("0042dd84ff4d4246a0e3d06095392a86", infoCreate);
	}
	
}
