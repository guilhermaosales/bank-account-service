package io.github.bankapi.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import io.github.bankapi.model.BankAccount;
import io.github.bankapi.model.dto.BankAccountResponse;

@Mapper
public interface BankAccountMapper {

    BankAccountMapper INSTANCE = Mappers.getMapper(BankAccountMapper.class);

    BankAccountResponse toResponse(BankAccount bankAccount);

}
