package lk.ijse.dep9.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Column(name = "full_name")
    private String fullName;
    @Id
    private String username;
    private String password;
    @Lob
    @Column(name = "profile_picture",columnDefinition = "MEDIUMBLOB")
    private Blob profilePicture;
}
