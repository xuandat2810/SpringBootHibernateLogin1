package com.example.springboothibernatelogin.dao;

import com.example.springboothibernatelogin.entity.Person;
import com.example.springboothibernatelogin.exception.LoginTransactionException;
import com.example.springboothibernatelogin.model.PersonInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class PersonAccountDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Person findPersonAccount(String userName, String password){
        String hql = "from Person where userName=:userName and password=:password";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("userName", userName);
        query.setParameter("password", password);
        Person person = (Person) query.uniqueResult();
        return person;
    }

    public boolean checkAccount(String userName, String password) throws LoginTransactionException {
        Person person = this.findPersonAccount(userName, password);
        if(person == null || person.getPassword() != password){
            throw new LoginTransactionException("Account not found " + userName);
        }else{
            return true;
        }
    }

    public List<PersonInfo> listPersonAccountInfo(){
        String sql = "Select new" + PersonInfo.class.getName()
                + "(e.id, e.userName, e.password, e.name, e.age, e.phone)"//
                + "from" + Person.class.getName() + "as e ";
        //String sql1 = "select * form Person";
        Session session = this.sessionFactory.getCurrentSession();
        Query<Person> query = session.createQuery("from Person");
        return query.getResultList().stream().map(o->
            new PersonInfo(o.getId(), o.getUserName(), o.getPassword(), o.getName(), o.getAge(), o.getPhone())
        ).collect(Collectors.toList());
    }

    //@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
//    public void addAccount(String nameUser, String password, String name, int age, int phone){
    public void addAccount(Person person){
        Session session = this.sessionFactory.openSession();
        session.persist(person);
    }

   // @Transactional
    public void deleteAccount(int id){
        Session session = this.sessionFactory.openSession();
        Person person = session.get(Person.class, id);
        session.delete(person);
    }

}
