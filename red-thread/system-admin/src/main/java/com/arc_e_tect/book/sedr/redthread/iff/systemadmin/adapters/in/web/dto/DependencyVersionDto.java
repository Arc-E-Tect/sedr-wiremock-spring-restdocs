package com.arc_e_tect.book.sedr.redthread.iff.systemadmin.adapters.in.web.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class DependencyVersionDto {
    String versionName;
    Double versionCode;
}
