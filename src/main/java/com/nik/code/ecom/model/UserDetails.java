package com.nik.code.ecom.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_details")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL)
    private List<Cart> userCartProducts;

    @OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL)
    private List<Order> userOrderProducts;

    @OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL)
    private List<WishList> userWishlistProducts;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "token_id")
    private AuthenticationToken token;

    @OneToOne(mappedBy = "userDetails", cascade = CascadeType.ALL)
    private User user;

    public UserDetails() {
    }

    public UserDetails(Address address, AuthenticationToken token) {
        this.address = address;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Cart> getUserCartProducts() {
        return userCartProducts;
    }

    public void setUserCartProducts(List<Cart> cartProducts) {
        this.userCartProducts = cartProducts;
    }

    public List<Order> getUserOrderProducts() {
        return userOrderProducts;
    }

    public void setUserOrderProducts(List<Order> userOrderProducts) {
        this.userOrderProducts = userOrderProducts;
    }

    public List<WishList> getUserWishlistProducts() {
        return userWishlistProducts;
    }

    public void setUserWishlistProducts(List<WishList> userWishlistProducts) {
        this.userWishlistProducts = userWishlistProducts;
    }

    public AuthenticationToken getToken() {
        return token;
    }

    public void setToken(AuthenticationToken token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
