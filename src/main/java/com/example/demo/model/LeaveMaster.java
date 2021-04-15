package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.demo.Utils.enums.LeaveTypes;

import com.sun.istack.Nullable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//import com.example.demo.Utils.enums;
@Getter
@Setter
@Entity
@Table(name="Leave_Master")
public class LeaveMaster {
	@javax.persistence.Id
	@GeneratedValue
	public int id;

	@Enumerated(EnumType.ORDINAL)
	public LeaveTypes leaveType;

	public int MaxLeavesPerYear;

	@Temporal(TemporalType.TIME)
	public java.util.Date effectiveFrom;

	@Nullable
	public java.util.Date effectiveTill;

}
