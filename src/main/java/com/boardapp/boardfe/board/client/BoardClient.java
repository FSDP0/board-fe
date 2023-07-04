package com.boardapp.boardfe.board.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "BOARD-CLIENT", url = "${api.url}")
public class BoardClient {

}
