package com.fernando.zssn.mapper.contract;

public interface IMapper <I, O>{
    O map(I in);
}