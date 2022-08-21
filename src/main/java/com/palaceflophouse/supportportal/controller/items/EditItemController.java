package com.palaceflophouse.supportportal.controller.items;

import com.palaceflophouse.supportportal.entities.ItemComment;
import com.palaceflophouse.supportportal.entities.SupportItem;
import com.palaceflophouse.supportportal.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: Brandon Shaffer
 * Date: 8/20/2022
 */
@Controller
@RequestMapping("/item")
public class EditItemController {

	private final ItemService itemService;

	public EditItemController(ItemService itemService) {
		this.itemService = itemService;
	}

	@GetMapping("/new-item")
	public String getNewItem(){
		return "new-item";
	}

	@PostMapping("/new")
	public String addNewItem(@RequestBody AddItemForm addItemForm){
		return "redirect:/user-homepage";
	}

	@GetMapping("/view/{itemId}")
	public String viewItem(@PathVariable Long itemId, Model model){
		SupportItem supportItem = itemService.loadItemById(itemId)
				.orElseThrow();

		model.addAttribute("supportItem", supportItem);

		List<ItemComment> comments = supportItem.getComments();
		model.addAttribute("comments", comments);

		return "view-item";
	}

	@PostMapping("/view/{itemId}/add-comment")
	public String addComment(@PathVariable Long itemId, AddCommentForm addCommentForm){
		SupportItem supportItem = itemService.loadItemById(itemId).orElseThrow();
		supportItem.addComment(new ItemComment());

		SupportItem savedItem = itemService.save(supportItem);

		return "redirect:/item/view/" + itemId;
	}
}
