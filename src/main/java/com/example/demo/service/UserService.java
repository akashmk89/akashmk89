package com.example.demo.service;

import com.example.demo.Utils.DTOs.UserAddEditDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Department;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<UserAddEditDTO> getAllUsers(){
        List<UserAddEditDTO> usersDTOs = new ArrayList<UserAddEditDTO>();
        List<User> users = (List<User>) userRepository.findAll();
        for (User user : users){
            UserAddEditDTO userDTO = new UserAddEditDTO();
            userDTO.setName(user.getName());
            userDTO.setEmail(user.getEmail());
            userDTO.setPassword(user.getPassword());
            userDTO.setEnabled(user.isEnabled());
            userDTO.setName(user.getName());
            userDTO.setPhoneNum(user.getPhoneNum());
            Set<Integer> roles = new HashSet<Integer>();
            for(Role role : user.getRoles()){
                roles.add(role.getId());
            }
            userDTO.setRoleIds(roles);
            userDTO.setDepartmentId(user.getDepartment().getId());
            userDTO.setDepartmentName(user.getDepartment().getName());
            usersDTOs.add(userDTO);
        }
        return  usersDTOs;
    }

    public User addUser(UserAddEditDTO userAddEditDTO) throws  ResourceNotFoundException{
        Department department = departmentRepository
                .findById(1)
                .orElseThrow(() -> new ResourceNotFoundException("Department with ID Not Found"));
        Role role = roleRepository.findById(1).orElseThrow(() -> new ResourceNotFoundException("Role with ID Not Found"));
        Set<Role> roles = new HashSet<Role>();
        roles.add(role);
        User user = new User();
        user.setName(userAddEditDTO.getName());
        user.setEmail(userAddEditDTO.getName());
        user.setEnabled(userAddEditDTO.isEnabled());
        user.setPhoneNum(userAddEditDTO.getPhoneNum());
        user.setPassword(userAddEditDTO.getPassword());
        user.setDepartment(department);
        user.setRoles(roles);
       return userRepository.save(user);

    }
}
