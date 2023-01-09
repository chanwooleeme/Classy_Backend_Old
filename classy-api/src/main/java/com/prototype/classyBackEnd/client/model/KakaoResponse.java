package com.prototype.classyBackEnd.client.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class KakaoResponse {

    private Long id;

    @Getter
    @Setter
    public static class Member {
        private long id;
        private Properties properties;
        private Account kakao_account;

        public String getKakaoEmail() {
            if(this.kakao_account != null) {
                return this.kakao_account.email;
            }
            return null;
        }

        public String getKakaoBirthday() {
            if(this.kakao_account != null) {
                return this.kakao_account.birthday;
            }
            return null;
        }

        public String getKakaoGender() {
            if(this.kakao_account != null) {
                return this.kakao_account.gender;
            }
            return null;
        }

        @Getter
        @Setter
        public class Properties {
            private String nickname;
        }

        @Getter
        @Setter
        public class Account {
            private Profile profile;
            private String email;
            private String birthday;
            private String gender;

            @Getter
            @Setter
            public class Profile {
                private String nickname;
            }
        }
    }
}
