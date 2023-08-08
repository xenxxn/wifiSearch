package com.zen.wifisearch.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Bookmark {
    private int BM_ID;
    private String BG_BM_NAME; //북마크그룹 FK
    private String WF_BM_NAME; // 와이파이 FK
    private Date BM_RG_DATE;
}
