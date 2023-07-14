package com.alan.controller;

import com.alan.Constans;
import com.alan.Response;
import com.alan.entity.Tag;
import com.alan.repository.TagRepository;

import java.util.List;

public class TagController {
    private TagRepository tagRepository = TagRepository.getInstance();
    public Response<Tag> createTag(Tag tag){
        try{
            Tag tagToSave = new Tag(tag.getName());
            tagRepository.add(tagToSave);
            return new Response<>(true, Constans.MESSAGE_OK, Constans.CODE_OK, tagToSave);
        }catch (Exception e){
            return new Response<>(false, Constans.MESSAGE_WRONG, Constans.CODE_WRONG);
        }
    }

    public Response modifyTag(int id, Tag tag){
        try{
            tagRepository.modifyById(id, tag);
            return new Response<>(true, Constans.MESSAGE_OK, Constans.CODE_OK);
        }catch (Exception e){
            return new Response<>(false, Constans.MESSAGE_WRONG, Constans.CODE_WRONG);
        }
    }

    public Response deleteTag(int id){
        try{
            tagRepository.deleteById(id);
            return new Response<>(true, Constans.MESSAGE_OK, Constans.CODE_OK);
        }catch (Exception e){
            return new Response<>(false, Constans.MESSAGE_WRONG, Constans.CODE_WRONG);
        }
    }

    public Response<List<Tag>> getTags(){
        return new Response<>(true ,Constans.MESSAGE_OK, Constans.CODE_OK, tagRepository.getEntities());
    }


}
