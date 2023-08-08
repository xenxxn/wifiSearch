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
public class BookmarkGroup {
    private int BG_ID;
    private String BG_NAME;
    private int BG_ORDER;
    private Date BG_RG_DATE;
    private Date BG_MD_DATE;
}
