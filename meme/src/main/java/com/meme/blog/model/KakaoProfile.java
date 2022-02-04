//-----------------------------------com.meme.blog.model.KakaoProfile.java-----------------------------------
package com.meme.blog.model;

import lombok.Data;

@Data
public class KakaoProfile {

	public Integer id;
	public String connected_at;
	public Properties properties;
	public Kakao_account kakao_account;

	//-----------------------------------com.meme.blog.model.Properties.java-----------------------------------
	@Data
	public class Properties {

		public String nickname;
		public String profile_image;
		public String thumbnail_image;

	} // Properties

	//-----------------------------------com.meme.blog.model.KakaoAccount.java-----------------------------------
	@Data
	public class Kakao_account {

		public Boolean profile_nickname_needs_agreement;
		public Boolean profile_image_needs_agreement;
		public Profile profile;
		public Boolean has_email;
		public Boolean email_needs_agreement;
		public Boolean is_email_valid;
		public Boolean is_email_verified;
		public String email;
		
		@Data
		public class Profile {

			public String nickname;
			public String thumbnail_image_url;
			public String profile_image_url;
			public Boolean is_default_image;

		} // Profile
	} // KakaoAccount
} // KakaoProfile







