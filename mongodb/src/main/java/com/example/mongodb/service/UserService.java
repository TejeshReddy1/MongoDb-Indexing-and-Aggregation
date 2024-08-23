package com.example.mongodb.service;



import com.example.mongodb.model.User;
import com.example.mongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public User saveUser(User user) {
        user.setCreatedAt(new Date());
        return userRepository.save(user);
    }

    public List<User> findUsersByName(String name) {
        return userRepository.findByName(name);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public double getAverageAge() {
        Aggregation aggregation = newAggregation(
                group().avg("age").as("averageAge")
        );
        AggregationResults<User> results = mongoTemplate.aggregate(aggregation, "users", User.class);
        return results.getMappedResults().isEmpty() ? 0 : results.getMappedResults().get(0).getAge();
    }
}

