package miu.edu.attendance.security;
import miu.edu.attendance.config.JPAUserDetails;
import miu.edu.attendance.domain.*;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public final class SecurityUtils {

//    public static String getSSN() {
//        JPAUserDetails userDetails = (JPAUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return userDetails.getSsn();
//    }

    public static JPAUserDetails getUserDetail() {
        return (JPAUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static Optional<Personnel> getPersonnel() {
        JPAUserDetails userDetails = (JPAUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        for (PersonRole role : userDetails.getRoles()){
            if (role.getName().equalsIgnoreCase("PERSONNEL") && role.isActive()) {
                return Optional.of((Personnel)role);
            }
        }
        return Optional.empty();
    }
    public static Optional<Faculty> getFaculty() {
        JPAUserDetails userDetails = (JPAUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        for (PersonRole role : userDetails.getRoles()){
            if (role.getName().equalsIgnoreCase("FACULTY") && role.isActive()) {
                return Optional.of((Faculty)role);
            }
        }
        return Optional.empty();
    }
    public static Optional<Student> getStudent() {
        JPAUserDetails userDetails = (JPAUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        for (PersonRole role : userDetails.getRoles()){
            if (role.getName().equalsIgnoreCase("STUDENT") && role.isActive()) {
                return Optional.of((Student)role);
            }
        }
        return Optional.empty();
    }

    public static Optional<Admin> getAdmin() {
        JPAUserDetails userDetails = (JPAUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        for (PersonRole role : userDetails.getRoles()){
            if (role.getName().equalsIgnoreCase("ADMIN") && role.isActive()) {
                return Optional.of((Admin)role);
            }
        }
        return Optional.empty();
    }
//
//    public static boolean isOrganizationUser() {
//        JPAUserDetails userDetails = (JPAUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return userDetails.getRoles().get(0).getName().equals("ORGANIZATION");
//    }
}
