package com.mobyeoldol.starcast.place.domain;

import com.mobyeoldol.starcast.place.domain.enums.PlaceType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Place {
    @Id
    @Column(name = "place_uid", length = 36, nullable = false)
    private String placeUid;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "type", length = 20, nullable = false)
    private PlaceType type;

    @Column(name = "address1", length = 20, nullable = false)
    private String address1;

    @Column(name = "address2", length = 20, nullable = false)
    private String address2;

    @Column(name = "address3", length = 20, nullable = false)
    private String address3;

    @Column(name = "phone_num", length = 20)
    private String phoneNum;

    @Column(name = "web_address", length = 100)
    private String webAddress;

    @Column(name = "image", length = 100)
    private String image;
}
