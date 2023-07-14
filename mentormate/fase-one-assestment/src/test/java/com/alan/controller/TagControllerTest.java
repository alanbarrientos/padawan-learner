package com.alan.controller;

import com.alan.entity.Tag;
import com.alan.repository.TagRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TagControllerTest {

    Tag tagDto;
    TagController tagController;
    TagRepository tagRepository = TagRepository.getInstance();
    @BeforeEach
    public void initializateTagController(){
        tagDto = new Tag("Animals");
        tagController = new TagController();
    }
    @Test
    public void shouldCreateTag(){
        Tag tagCreated = tagController.createTag(tagDto).getBody();
        Assertions.assertTrue(tagController.getTags().getBody().contains(tagCreated));
    }


    @Test
    public void shouldDeleteTag(){
        Tag tagCreated = tagController.createTag(tagDto).getBody();
        Assertions.assertTrue(tagController.getTags().getBody().contains(tagCreated));
        tagController.deleteTag(tagCreated.getId());
        Assertions.assertFalse(tagController.getTags().getBody().contains(tagCreated));
    }
    @Test
    public void shouldReturnResponseUnsuccessfulTryingToDeleteThatDontExist(){
        Assertions.assertFalse(tagController.deleteTag(tagDto.getId()).isSuccessful());
    }


    @Test
    public void shouldModifyTag(){
        Tag tagCreated = tagController.createTag(tagDto).getBody();
        Assertions.assertTrue(tagController.getTags().getBody().contains(tagCreated));
        tagCreated.setName("Monkeys");
        tagController.modifyTag(tagCreated.getId(), tagCreated);
        Assertions.assertEquals("Monkeys",
                tagRepository.getById(tagCreated.getId()).orElse( new Tag("Not Found")).getName()
        );
    }
    @Test
    public void shouldReturnResponseUnsuccessfulTryingToModifyTagThatDontExist(){
        Tag tagCreated = tagController.createTag(tagDto).getBody();
        Assertions.assertTrue(tagController.getTags().getBody().contains(tagCreated));
        tagCreated.setName("Monkeys");
        tagController.deleteTag(tagCreated.getId());
        Assertions.assertFalse(tagController.modifyTag(tagCreated.getId(), tagCreated).isSuccessful());
    }


}
