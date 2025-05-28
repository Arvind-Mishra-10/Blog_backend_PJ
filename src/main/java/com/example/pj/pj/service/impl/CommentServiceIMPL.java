//package com.example.pj.pj.service.impl;
//
//import com.example.pj.pj.entity.Comment;
//import com.example.pj.pj.entity.Post;
//import com.example.pj.pj.exception.ResourceNotFound;
//import com.example.pj.pj.payload.CommentDto;
//import com.example.pj.pj.payload.PostDto;
//import com.example.pj.pj.repository.CommentRepository;
//import com.example.pj.pj.repository.PostRespository;
//import com.example.pj.pj.service.CommentService;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class CommentServiceIMPL implements CommentService {
//
//    @Autowired
//    private CommentRepository commentRepository;
//
//    @Autowired
//    private PostRespository postRepo;
//
//
//
//    @Override
//    public CommentDto createComment(long postId, CommentDto commentDto) {
//        Post post = postRepo.findById(postId).orElseThrow(
//                () -> new ResourceNotFound("Post id is Not Present: " + postId)
//        );
//
////        Comment comment = new Comment();
//        Comment comment = maptoEntity(commentDto);
////        modelMapper.map(commentDto,comment);
////        comment.setName(commentDto.getName());
////        comment.setEmail(commentDto.getEmail());
////        comment.setBody(commentDto.getBody());
//        comment.setPost(post);
//
//        try {
//            Comment savedComment = commentRepository.save(comment);
//            return maptoDto(savedComment);
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to save the comment", e);
//        }
//    }
//
//    @Override
//    public void deleteCode(long id) {
//        Comment comment1 = commentRepository.findById(id).orElseThrow(
//                () -> new ResourceNotFound("Comment is Not Present: " + id)
//        );
//        commentRepository.deleteById(id);
//
//
//    }
//
//    @Override
//    public CommentDto updateComment(long id, CommentDto commentDto) {
//        Comment comment = commentRepository.findById(id).orElseThrow(
//                () -> new ResourceNotFound("Comment Not Found " + id));
//        comment.setBody(commentDto.getBody());
//        try {
//            Comment sivedComment = commentRepository.save(comment);
//            return maptoDto(sivedComment);
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to save the comment", e);
//        }
//    }
//
////    dto to entity
//    private Comment maptoEntity(CommentDto comme){
//        Comment mapped = modelMapper.map(comme, Comment.class);
//        return mapped;
//    }
//
////    entity to Dto methord
//    @Autowired
//    private ModelMapper modelMapper;
//    private CommentDto maptoDto(Comment comm) {
//        CommentDto dto = modelMapper.map(comm, CommentDto.class);
////        CommentDto dto = new CommentDto();
////        dto.setId(comm.getId());
////        dto.setName(comm.getName());
////        dto.setEmail(comm.getEmail());
////        dto.setBody(comm.getBody());
//        return dto;
//    }
//
//    }
//
