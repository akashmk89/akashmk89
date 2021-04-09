package com.example.demo.Utils.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeaveSummaryDTO {
    public String userName;
    public int userId;
    public String departmentName;
    public int departmentId;
    public int availedCasualLeaves;
    public int availedEarnedLeaves;
}
