package ua.goit.service;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import java.util.Optional;
import java.util.UUID;
import ua.goit.controller.dto.RegistrationRequestDto;
import ua.goit.controller.dto.RegistrationResponseDto;

public class AuthService {
    
    private final BiMap<String, RegistrationRequestDto> userMap = HashBiMap.create();
    
    public synchronized RegistrationResponseDto register(RegistrationRequestDto dto) {
        BiMap<RegistrationRequestDto, String> inverse = userMap.inverse();
        if (inverse.containsKey(dto)) {
            throw new RuntimeException();
        }
        String token = UUID.randomUUID().toString();
        userMap.put(token, dto);
        return toResponseDto(dto.getUsername(), token);
    }
    
    public synchronized Optional<RegistrationResponseDto> authorize(String token) {
        return Optional.ofNullable(userMap.get(token))
                .map(user -> toResponseDto(user.getUsername(), token));
        
    }
    
    private RegistrationResponseDto toResponseDto(String username, String token) {
        return RegistrationResponseDto.builder()
                .token(token)
                .username(username)
                .build();
    }
    
}
