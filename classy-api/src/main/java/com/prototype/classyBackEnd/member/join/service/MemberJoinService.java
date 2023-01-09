package com.prototype.classyBackEnd.member.join.service;

import com.prototype.classyBackEnd.member.domain.dto.MemberResponse;
import com.prototype.classyBackEnd.member.join.service.dtos.MemberJoinRequest;

public interface MemberJoinService {
    MemberResponse joinMember(MemberJoinRequest request);
}
