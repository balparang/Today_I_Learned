package com.example.dddstart.domain;

import jakarta.persistence.*;

@Embeddable
public class Orderer {

    // MemberId에 정의된 컬럼 이름을 변경하기 위해 AttributeOverride 사용
    @Embedded
    @AttributeOverrides(
            @AttributeOverride(name = "id", column = @Column(name = "orderer_id"))
    )
    private MemberId memberId; // Orderer의 memberId는 Member 애그리거트를 ID로 참조.

    public void cancel() {

    }
}
