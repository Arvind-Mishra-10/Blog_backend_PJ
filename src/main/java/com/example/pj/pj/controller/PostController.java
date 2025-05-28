//package com.example.pj.pj.controller;
//
//import com.example.pj.pj.payload.PostDto;
//import com.example.pj.pj.payload.PostResponse;
//import com.example.pj.pj.service.PostService;
//import jakarta.validation.Valid;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/posts")
//public class PostController {
//
//
//    private PostService postService;
//    //Constructor based injection
//    public PostController(PostService postService) {
//        this.postService = postService;
//    }
//        //http://localhost:8080/api/posts
//       @PreAuthorize("hasRole('ADMIN')")
//    @PostMapping
//    public ResponseEntity<?>createPost(
//            @Valid @RequestBody PostDto postDto,
//            BindingResult result){
//        if(result.hasErrors()){
//            return  new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        PostDto dto = postService.createpost(postDto);
//        return new ResponseEntity<>(dto, HttpStatus.CREATED);
//    }
//    //http://localhost:8080/api/posts/{postId}
//    @DeleteMapping("{postId}")
//    public ResponseEntity<Object>deletePostById(@PathVariable int postId){
//        PostDto deletedPost = postService.deletePostById(postId);
//        return new ResponseEntity<>(Map.of("message", "Post is Deleted with id: " + postId,
//                                            "deletedPost", deletedPost), HttpStatus.OK);
//    }
//    @GetMapping("{postId}")
//    public ResponseEntity<Object>getPostByPostId(@PathVariable int postId){
//        PostDto post = postService.getPostById(postId);
//        return new ResponseEntity<>(post,HttpStatus.CREATED);
//    }
//    //http://localhost:8080/api/posts?pageNo=0&pageSize=3&sortBy=title&sortDir=desc
//    @GetMapping
//    public PostResponse getAllPosts(
//            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
//            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
//            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
//            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
//
//        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
//    }
//
//
//
//    @PutMapping("{postId}")
//    public ResponseEntity<Object> updatePost(
//            @PathVariable int postId,
//            @Valid @RequestBody PostDto postDto,
//            BindingResult result1     ) {
//        if(result1.hasErrors()){
//            return  new ResponseEntity<>(result1.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        PostDto postDto1 = postService.updatePost(postId, postDto);
//
//        return new ResponseEntity<>(Map.of("message", "Post is Deleted with id: " + postId,
//                "UpdatedPost", postDto1), HttpStatus.OK);
//    }
//
//
//
//}
//
