package com.yam.funteer.donation.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DonationServiceImpl{

	// private final PostRepository postRepository;
	// private final CategoryRepository categoryRepository;
	// private final PaymentRepository paymentRepository;
	// // private final MoneyReposiroty moneyReposiroty;
	// // private final PostHashtagRepository postHashtagRepository;
	// private final HashtagRepository hashtagRepository;
	//
	// public List<Post> donationGetList(DonationGetListReq donationGetListReq) {
	// 	List<Post>donationList=new ArrayList<>();
	// 	String category=donationGetListReq.getCategory();
	// 	String hashtag=donationGetListReq.getHashtag();
	// 	String keyword=donationGetListReq.getKeyword();
	//
	// 	if(category==null&&hashtag==null&&keyword==null){
	// 		// donationList=postRepository.findAllByCode();
	// 	} else if(category==null&&hashtag==null&&keyword!=null){
	// 		// donationList=postRepository.findAllByKeyword(keyword);
	// 	}
	//
	// 	return donationList=postRepository.findAll();
	// }
	//
	// public Post donationGetDetail(Long postId) {
	// 	return postRepository.findById(postId).get();
	// }
	//
	// public Payment donationJoin( Long postId,DonationJoinReq donationJoinReq) {
	// 	Payment payment=new Payment();
	// 	Post post=postRepository.findById(postId).get();
	// 	payment.setPost(post);
	// 	payment.setDate(LocalDateTime.now());
	// 	payment.setAmount(donationJoinReq.getPaymentAmount());
	//
	// 	paymentRepository.save(payment);
	// 	return payment;
	// }
	//
	// public Post donationRegister(DonationRegisterReq donationRegisterReq) {
	//
	// 	Post post=new Post();
	// 	post.setContent(donationRegisterReq.getContent());
	// 	post.setTitle(donationRegisterReq.getTitle());
	// 	post.setDate(LocalDateTime.now());
	// 	post.setHit(Long.valueOf(0));
	// 	Category category=new Category();
	//
	// 	if(categoryRepository.findByName(donationRegisterReq.getCategory()).isPresent()){
	// 		category=categoryRepository.findByName(donationRegisterReq.getCategory()).get();
	// 	}else {
	// 		category.setName(donationRegisterReq.getCategory());
	// 		categoryRepository.save(category);
	// 	}
	// 	post.setCategory(category);
	// 	postRepository.save(post);
	//
	//
	// 	for(String ht:donationRegisterReq.getHashtags()){
	// 		Hashtag hashtag=new Hashtag();
	// 		if(!hashtagRepository.findByName(ht).isPresent()){
	// 			hashtag.setName(ht);
	// 			hashtagRepository.save(hashtag);
	//
	// 		}else {
	// 			hashtag=hashtagRepository.findByName(ht).get();
	// 		}
	//
	// 		PostHashtag postHashtag=new PostHashtag();
	// 		postHashtag.setHashtag(hashtag);
	// 		postHashtag.setPost(post);
	// 		postHashtagRepository.save(postHashtag);
	// 	}
	//
	//
	// 	Money money=new Money();
	// 	money.setAmount(donationRegisterReq.getAmount());
	// 	money.setDescription(donationRegisterReq.getContent());
	// 	money.setPost(post);
	//
	// 	moneyReposiroty.save(money);
	//
	// 	return post;
	// }
	//
	// public Boolean donationDelete(Long postId, Long adminId) {
	//
	// 	if(postRepository.findById(postId).get()!=null) {
	// 		//&&postRepository.findById(postId).get().getCode()== GroupCode.A01
	// 		Post post = postRepository.findById(postId).get();
	//
	// 		// Admin admin=adminRepository.findById(adminId).get();
	// 		// if(admin.getEmail()==)
	// 		postRepository.save(post);
	// 		return true;
	// 	}return false;
	// }
	//
	// public Boolean donationModify(Long postId, DonationRegisterReq donationModifyReq) {
	// 	if(postRepository.findById(postId).get()!=null){
	//
	// 		//&&postRepository.findById(postId).get().getCode()== GroupCode.A01
	// 		Post post = postRepository.findById(postId).get();
	// 		postHashtagRepository.deleteAllByPost(post);
	//
	// 		Money money=moneyReposiroty.findFirstByPost(post);
	// 		money.setAmount(donationModifyReq.getAmount());
	// 		money.setDescription(donationModifyReq.getContent());
	//
	// 		moneyReposiroty.save(money);
	//
	// 		Category category=new Category();
	// 		if(categoryRepository.findByName(donationModifyReq.getCategory()).isPresent()){
	// 			category=categoryRepository.findByName(donationModifyReq.getCategory()).get();
	// 		}else {
	// 			category.setName(donationModifyReq.getCategory());
	// 			categoryRepository.save(category);
	// 		}
	// 		post.setCategory(category);
	// 		post.setTitle(donationModifyReq.getTitle());
	// 		post.setContent(donationModifyReq.getContent());
	// 		postRepository.save(post);
	//
	//
	// 		for(String ht:donationModifyReq.getHashtags()){
	// 			Hashtag hashtag=new Hashtag();
	// 			if(!hashtagRepository.findByName(ht).isPresent()){
	// 				hashtag.setName(ht);
	// 				hashtagRepository.save(hashtag);
	//
	// 			}else {
	// 				hashtag=hashtagRepository.findByName(ht).get();
	// 			}
	//
	// 			PostHashtag postHashtag=new PostHashtag();
	// 			postHashtag.setHashtag(hashtag);
	// 			postHashtag.setPost(post);
	// 			postHashtagRepository.save(postHashtag);
	// 		}
	// 		return true;
	// 	}
	// 	return false;
	// }
}