// package com.wcy.rhapsody.admin.controller;
//
// import com.wcy.rhapsody.admin.core.R;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
//
// import java.io.IOException;
//
// /**
//  * 广播
//  *
//  * @author Yeeep 2020/11/12
//  */
// @RestController
// public class DemoController {
//
//     @GetMapping("index")
//     public R index() {
//         return R.ok();
//     }
//
//     @RequestMapping("/push/{toUserId}")
//     public R pushToWeb(String message, @PathVariable String toUserId) throws IOException {
//         // WebSocketServer.sendInfo(message, toUserId);
//         return R.ok().message("MSG SEND SUCCESS");
//     }
// }
