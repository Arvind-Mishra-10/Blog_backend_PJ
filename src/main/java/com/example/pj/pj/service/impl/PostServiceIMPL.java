//package com.example.pj.pj.service.impl;
//
//import com.example.pj.pj.entity.Post;
//import com.example.pj.pj.exception.ResourceNotFound;
//import com.example.pj.pj.payload.PostDto;
//import com.example.pj.pj.payload.PostResponse;
//import com.example.pj.pj.repository.PostRespository;
//import com.example.pj.pj.service.PostService;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class PostServiceIMPL implements PostService {
//
//    private PostRespository postRespository;
//
//    //Constructor based injection
//    public PostServiceIMPL(PostRespository postRespository) {
//        this.postRespository = postRespository;
//    }
//
//    @Override
//    public PostDto createpost(PostDto postDto) {
//
//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescribtion(postDto.getDescribtion());
//        post.setContent(postDto.getContent());
//        Post savedData = postRespository.save(post);
//
//
//        PostDto dto = new PostDto();
//        dto.setId(post.getId());
//        dto.setTitle(post.getTitle());
//        dto.setDescribtion(post.getDescribtion());
//        dto.setContent(post.getContent());
//        return dto;
//    }
//
//    @Override
//    public PostDto deletePostById(int postId) {
//
//        Post pst = postRespository.findById((long) postId).orElseThrow(
//                () -> new ResourceNotFound("Post not Found with  id : " + postId)
//        );
//        Optional<Post> post = postRespository.findById((long) postId);
//        if (post.isPresent()) {
//            Post host = post.get();
//            PostDto deletedPostDto = new PostDto(host.getId(), host.getTitle(), host.getDescribtion(), host.getContent());
//            postRespository.delete(host); // Delete the post
//            return deletedPostDto;
//
//        }
//        return null;
//    }
//
//    @Override
//    public PostDto getPostById(int postId) {
//        Post post = postRespository.findById((long) postId).orElseThrow(() -> new ResourceNotFound("Post not Found with  id : " + postId)
//        );
//        PostDto postDto = maptoDto(post);
//        return postDto;
//        //return maptoDto(post);
//    }
//
//    @Override
//    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
//        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
//                ? Sort.by(sortBy).ascending()
//                : Sort.by(sortBy).descending();
//
//        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
//        Page<Post> postPage = postRespository.findAll(pageable);
//
//        List<PostDto> postDtos = postPage.getContent()
//                .stream()
//                .map(this::maptoDto)
//                .collect(Collectors.toList());
//
//        PostResponse postResponse = new PostResponse();
//        postResponse.setContent(postDtos);
//        postResponse.setPageNo(postPage.getNumber());
//        postResponse.setPageSize(postPage.getSize());
//        postResponse.setTotalElement((int) postPage.getTotalElements());
//        postResponse.setTotalPages(postPage.getTotalPages());
//        postResponse.setLast(postPage.isLast());
//
//        return postResponse;
//    }
//
//
//
//
//    @Override
//    public PostDto updatePost(int postId , PostDto postDto) {
//        Optional<Post> byId = postRespository.findById((long) postId);
//        Post post = byId.orElseThrow(() -> new ResourceNotFound("Post not Found with  id : " + postId)
//        );
//        Post post1 = byId.get();
//        post1.setTitle(postDto.getTitle());
//        post1.setDescribtion(postDto.getDescribtion());
//        post1.setContent(postDto.getContent());
//        Post savedData = postRespository.save(post1);
//        PostDto postDto1 = maptoDto(post1);
//        return postDto1;
//    }
//
////    @Override
////    public CommentDto createcomment(CommentDto commentDto) {
////        Comment cmt = new Comment();
////        cmt.setTitle(commentDto.getTitle());
////        cmt.setContent(commentDto.getContent());
////        cmt.setDescribtion(commentDto.getDescribtion());
////
////
////        return null;
////    }
//@Autowired
//private ModelMapper modelMapper;
//
//    PostDto maptoDto (Post post){
//        //I used madel mapper here it reduces boiller plate code and convert object to object mapping from one class to other class objects
//        PostDto dto = modelMapper.map(post, PostDto.class);
////        PostDto dto =new PostDto();
////        dto.setId(post.getId());
////        dto.setTitle(post.getTitle());
////        dto.setDescribtion(post.getDescribtion());
////        dto.setContent(post.getContent());
//        return dto;
//    }
//}
//
//
//
