package com.techelevator.dao;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class JdbcTransferDaoTest extends BaseDaoTests {
    private JdbcTransferDao sut;
    private Transfer testTransfer;
    private Transfer testTransfer2;
    private AccountDao testAccountDao;

    @Before
    public void setup() {
        sut = new JdbcTransferDao(new JdbcTemplate(dataSource), testAccountDao);
        testTransfer = new Transfer( 2, 2, 2001, 2002, new BigDecimal("300.00"));
        testTransfer2 = new Transfer( 2, 2, 2002, 2001, new BigDecimal("50.00"));
    }
    //getAllTransfersById
    //getTransferById
    //sendTransfer
    //requestTransfer
    //getPendingRequests
    //updateTransferRequest
    @Test
    public void getAllTransfersById_returns_correct_transfers() {
        Transfer actual = sut.getTransferById(3001);
        Transfer expected = testTransfer;

        assertTransfersMatch(expected, actual);
    }
    @Test
    public void getTransfersByUserId_returns_correct_number_of_transfers() {
        //need mock data to test
        List<Transfer> transferList = sut.getAllTransfersByUserId(2001);
        Assert.assertEquals(transferList.size(), 2);
        assertTransfersMatch(testTransfer, transferList.get(0));
        assertTransfersMatch(testTransfer2, transferList.get(1));
    }

    private void assertTransfersMatch(Transfer expected, Transfer actual) {
        Assert.assertEquals(expected.getTransferId(), actual.getTransferId());
        Assert.assertEquals(expected.getTransferStatusId(), actual.getTransferStatusId());
        Assert.assertEquals(expected.getTransferTypeId(), actual.getTransferTypeId());
        Assert.assertEquals(expected.getAccountTo(), actual.getAccountTo());
        Assert.assertEquals(expected.getAmount(), actual.getAmount());
    }
}
