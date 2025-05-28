//package com.example.pj.pj.controller;
//
//
//import com.example.pj.pj.payload.CommentDto;
//import com.example.pj.pj.payload.PostDto;
//import com.example.pj.pj.service.CommentService;
//import com.example.pj.pj.service.PostService;
//import jakarta.validation.Valid;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/comment")
//
//
//public class CommentController {
//    private CommentService commentService;
//
//    public CommentController(CommentService commentService) {
//        this.commentService = commentService;
//    }
//
//    //Constructor based injection
//    //http://localhost:8080/api/comment/idnumber
//    @PostMapping("{postId}")
//    public ResponseEntity<Object> saveComment(@PathVariable long postId,
//                                                  @Valid @RequestBody CommentDto commentDto,
//                BindingResult resultCmt){
//        if (resultCmt.hasErrors()) {
//
//            return new ResponseEntity<>(resultCmt.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        CommentDto dto = commentService.createComment(postId, commentDto);
//        return new ResponseEntity<>(dto, HttpStatus.CREATED);
//    }
//    @DeleteMapping("{id}")
//    public ResponseEntity<String> deleteComment(@PathVariable long id){
//        commentService.deleteCode(id);
//        return new ResponseEntity<>("Comment is Deleted with id:"+id, HttpStatus.OK);
//    }
//    @PutMapping("{id}")
//    public ResponseEntity<Object>updateComment(@PathVariable long id, @Valid @RequestBody CommentDto commentDto
//                  ,BindingResult result  ){
//        if (result.hasErrors()){
//            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        CommentDto dto = commentService.updateComment(id, commentDto);
//        return new ResponseEntity<>(dto,HttpStatus.CREATED);
//    }
//}
