package com.surya.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import com.surya.entity.RefreshToken;
import java.util.Optional;


public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Integer> 
{
    Optional<RefreshToken> findByToken(String token);
}