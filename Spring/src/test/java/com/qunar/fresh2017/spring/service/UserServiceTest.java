package com.qunar.fresh2017.spring.service;

import com.qunar.fresh2017.spring.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class UserServiceTest {

    @Mock
    protected UserService userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRemoveById() throws Exception {
        Mockito.when(userService.removeById(1)).thenReturn(1);
        int remove = userService.removeById(1);
        Assert.assertEquals(remove, 1);
        Mockito.verify(userService).removeById(1);
    }

    @Test
    public void testFindById() throws Exception {
        User user = new User();
        user.setId(1);
        user.setName("mockTest");
        Mockito.when(userService.findById(1)).thenReturn(user);
        User user1 = userService.findById(1);
        Assert.assertEquals(user, user1);
        Mockito.verify(userService).findById(1);
    }

    @Test
    public void testFindAll() throws Exception {
        User user = new User();
        user.setId(1);
        user.setName("mockTest");
        List<User> userList = new ArrayList<User>();
        userList.add(user);
        Mockito.when(userService.findAll()).thenReturn(userList);
        Assert.assertEquals(userService.findAll(), userList);
        Mockito.verify(userService).findAll();
    }

    @Test
    public void testSave() throws Exception {
        User user = new User();
        user.setName("mockTest");
        Mockito.when(userService.save(user)).thenReturn(user);
        User user1 = userService.save(user);
        Assert.assertEquals(user, user1);
        Mockito.verify(userService).save(user);
    }

    @Test
    public void testUpdate() throws Exception {
        User user = new User();
        user.setId(1);
        user.setName("mockTest");
        Mockito.when(userService.update(user)).thenReturn(user);
        User user1 = userService.update(user);
        Assert.assertEquals(user, user1);
        Mockito.verify(userService).update(user);
    }
}