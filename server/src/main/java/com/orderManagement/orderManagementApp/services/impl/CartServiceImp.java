package com.orderManagement.orderManagementApp.services.impl;

import com.orderManagement.orderManagementApp.dao.CartDao;
import com.orderManagement.orderManagementApp.dao.CartItemDao;
import com.orderManagement.orderManagementApp.dao.MenuDao;
import com.orderManagement.orderManagementApp.dto.CartItemDetails;
import com.orderManagement.orderManagementApp.dto.CartItemQuantityUpdateRequest;
import com.orderManagement.orderManagementApp.dto.CartRequest;
import com.orderManagement.orderManagementApp.dto.CartResponse;
import com.orderManagement.orderManagementApp.model.CartItem;
import com.orderManagement.orderManagementApp.model.MenuItem;
import com.orderManagement.orderManagementApp.model.UserCart;
import com.orderManagement.orderManagementApp.services.CartService;
import com.orderManagement.orderManagementApp.utils.ApiResponse;
import com.orderManagement.orderManagementApp.utils.IdGeneratorUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImp implements CartService {
    @Autowired
    CartDao cartDao;
    @Autowired
    CartItemDao cartItemDao;

    @Autowired
    MenuDao menuDao;

    @Override
    public ApiResponse<CartResponse> getAllCartItems(String userId) {
        try {
            if (cartDao.existsByUserId(userId)) {
                UserCart userCart = cartDao.findByUserId(userId);
                List<CartItem> cartItemList = cartItemDao.findByCartId(userCart.getCartMappingId());
                List<CartItemDetails> cartItemDetailsList = new ArrayList<>();
                for (CartItem c : cartItemList){
                    CartItemDetails cartItemDetails = new CartItemDetails(c,menuDao.findByItemId(c.getItemId()).getItemName(),menuDao.findByItemId(c.getItemId()).getItemPrice());
                    cartItemDetailsList.add(cartItemDetails);
                }
                CartResponse cartResponse = new CartResponse(userId, userCart.getCartMappingId(), cartItemDetailsList);
                return new ApiResponse<>(HttpStatus.OK.value(), cartResponse);
            } else {
                return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "User id invalid");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), "INTERNAL_SERVER_ERROR");
        }
    }

    @Override
    public ApiResponse<CartResponse> addToCart(CartRequest request) {
        System.out.println(request);
        try {
            String cartId = "";
            if (cartDao.existsByUserId(request.getUserId())) {
                UserCart existingUserCart = cartDao.findByUserId(request.getUserId());
                cartId = existingUserCart.getCartMappingId();
                System.out.println("1" + cartId + " " + request.getUserId());

                List<CartItem> existingCartItemList = cartItemDao.findByCartId(cartId);
                for (CartItem c : existingCartItemList){
                    if (c.getItemId() == request.getItemId()){
                        return increaseItemQuantity(new CartItemQuantityUpdateRequest(request.getUserId(), request.getItemId()));
                    }
                }
            } else {
                cartId = IdGeneratorUtil.generateId("crt");
                UserCart userCart = new UserCart();
                System.out.println("2" + cartId + " " + request.getUserId());
                userCart.setCartMappingId(cartId);
                userCart.setUserId(request.getUserId());
                cartDao.save(userCart);
            }
            CartItem newItem = new CartItem();
            newItem.setCartDetailMapping(IdGeneratorUtil.generateId("crtitm"));
            newItem.setCartId(cartId);
            newItem.setItemId(request.getItemId());
            newItem.setQuantity(request.getQuantity());
            cartItemDao.save(newItem);

            return new ApiResponse<>(HttpStatus.OK.value(), "Item added to cart successfully");

        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }

    @Override
    public ApiResponse<CartResponse> increaseItemQuantity(CartItemQuantityUpdateRequest request) {
        try {
            String cartId = "";
            if (cartDao.existsByUserId(request.getUserId())) {
                UserCart existingUserCart = cartDao.findByUserId(request.getUserId());
                cartId = existingUserCart.getCartMappingId();
            } else {
                return new ApiResponse<>(HttpStatus.BAD_REQUEST.value());
            }
            List<CartItem> cartItemList = cartItemDao.findByCartId(cartId);
            for (CartItem c : cartItemList) {
                if (c.getItemId() == request.getItemId()) {
                    c.setQuantity(c.getQuantity() + 1);
                    cartItemDao.save(c);
                }
            }
            return new ApiResponse<>(HttpStatus.OK.value(), "Item quantity updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }

    @Override
    public ApiResponse<CartResponse> decreaseItemQuantity(CartItemQuantityUpdateRequest request) {
        try {
            String cartId = "";
            if (cartDao.existsByUserId(request.getUserId())) {
                UserCart existingUserCart = cartDao.findByUserId(request.getUserId());
                cartId = existingUserCart.getCartMappingId();
            } else {
                return new ApiResponse<>(HttpStatus.BAD_REQUEST.value());
            }
            List<CartItem> cartItemList = cartItemDao.findByCartId(cartId);
            for (CartItem c : cartItemList) {
                if (c.getItemId() == request.getItemId()) {
                    c.setQuantity(c.getQuantity() - 1);
                    cartItemDao.save(c);
                }
            }
            return new ApiResponse<>(HttpStatus.OK.value(), "Item quantity updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }

    @Override
    public ApiResponse<CartResponse> deleteItem(CartItemQuantityUpdateRequest request) {
        try {
            String cartId = "";
            if (cartDao.existsByUserId(request.getUserId())) {
                UserCart existingUserCart = cartDao.findByUserId(request.getUserId());
                cartId = existingUserCart.getCartMappingId();
            } else {
                return new ApiResponse<>(HttpStatus.BAD_REQUEST.value());
            }
            List<CartItem> cartItemList = cartItemDao.findByCartId(cartId);
            for (CartItem c : cartItemList) {
                if (c.getItemId() == request.getItemId()) {
                    cartItemDao.delete(c);
                }
            }
            return new ApiResponse<>(HttpStatus.OK.value(), "Item quantity updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }
    @Transactional
    @Override
    public ApiResponse<String> clearCart(String userId) {
        try {
            String cartId = "";
            if (cartDao.existsByUserId(userId)) {
                UserCart existingUserCart = cartDao.findByUserId(userId);
                cartId = existingUserCart.getCartMappingId();
            } else {
                return new ApiResponse<>(HttpStatus.BAD_REQUEST.value());
            }
            cartItemDao.deleteAllByCartId(cartId);
            return new ApiResponse<>(HttpStatus.OK.value(), "Item quantity updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }

    @Override
    public ApiResponse<Double> calculateCartValue(String userId) {
        try {
            String cartId = "";
            if (cartDao.existsByUserId(userId)) {
                UserCart existingUserCart = cartDao.findByUserId(userId);
                cartId = existingUserCart.getCartMappingId();
            } else {
                return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),0.0);
            }
            List<CartItem> cartItemList = cartItemDao.findByCartId(cartId);
            double totalAmount = 0.0;
            for (CartItem c : cartItemList) {
                MenuItem menuItem = menuDao.findByItemId(c.getItemId());
                double curAmount = menuItem.getItemPrice() * c.getQuantity();
                totalAmount += curAmount;
            }
            return new ApiResponse<>(HttpStatus.OK.value(),totalAmount);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }
}
