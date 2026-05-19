package com.arc_e_tect.book.sedr.redthread.iff.systemadmin.adapters.out.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DependencyVersionResponse {
    private String versionName;
    private Double versionCode;
}
