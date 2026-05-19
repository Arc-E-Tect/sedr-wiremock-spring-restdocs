package com.arc_e_tect.book.sedr.redthread.iff.relationship.adapters.in.web.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class VersionResponse {
    String versionName;
    double versionCode;
}
