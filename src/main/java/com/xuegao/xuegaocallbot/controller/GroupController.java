package com.xuegao.xuegaocallbot.controller;

import com.xuegao.xuegaocallbot.domain.GroupDTO;
import com.xuegao.xuegaocallbot.service.OneToMoreCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuegao
 * @version 1.0
 * @date 2021/11/27 16:57
 */
@RestController
public class GroupController {

    @Autowired
    private OneToMoreCallService oneToMoreCallService;

    @PostMapping("/chat/create")
    public String createChat(@RequestBody GroupDTO groupDTO) {
        return oneToMoreCallService.createChat(groupDTO);
    }


}