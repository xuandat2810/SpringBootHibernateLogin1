package com.example.springboothibernatelogin.dao;

import com.example.springboothibernatelogin.entity.Person;
import com.example.springboothibernatelogin.exception.LoginTransactionException;
import com.example.springboothibernatelogin.form.PersonForm;
import com.example.springboothibernatelogin.form.PersonUpdateForm;
import com.example.springboothibernatelogin.model.PersonInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
//Nguyen Xuaan DDatj
@Repository
@Transactional
public class PersonAccountDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Person findById(int id){
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }

    /*public Person findPersonAccount(String userName, String password){
        String hql = "from Person where userName=:userName and password=:password";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("userName", userName);
        query.setParameter("password", password);
        Person person = (Person) query.uniqueResult();
        System.out.println("user name: " + person.getUserName() + "password: " + person.getPassword());
        return person;
    }

    public boolean checkAccount(String userName, String password){
        Person person = this.findPersonAccount(userName, password);
        if(person.getUserName().contains(userName)){
            return true;
        }else{
            return false;
        }
    }*/

    //commit thu
    public List<PersonInfo> listPersonAccountInfo(){
        Session session = this.sessionFactory.getCurrentSession();
        Query<Person> query = session.createQuery("from Person");
        return query.getResultList().stream().map(o->
            new PersonInfo(o.getId(), o.getUserName(), o.getPassword(), o.getName(), o.getAge(), o.getPhone())
        ).collect(Collectors.toList());
    }

    //@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
//    public void addAccount(String nameUser, String password, String name, int age, int phone){
    public void addPerson(PersonForm personForm){
        Session session = this.sessionFactory.openSession();
        Person person = new Person();
        person.setUserName(personForm.getUserName());
        person.setPassword(personForm.getPassword());
        person.setName(personForm.getName());
        person.setAge(personForm.getAge());
        person.setPhone(personForm.getPhone());
        session.save(person);
        session.close();
        //session.persist(person);
    }
   // @Transactional
    public void deletePerson(int id){
        /*Session session = this.sessionFactory.openSession();
        Person person = session.get(Person.class, id);
        session.delete(person);*/
        Session session = sessionFactory.openSession();
        String hql = "delete from Person where id=:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        int d = query.executeUpdate();
        //session.clear();
        session.close();
    }

    @Transactional(rollbackFor = Exception.class)
    public void updatePerson(PersonUpdateForm personUpdateForm){
        Person person = this.findById(personUpdateForm.getId());

        person.setUserName(personUpdateForm.getUserName());
        person.setPassword(personUpdateForm.getPassword());
        person.setName(personUpdateForm.getName());
        person.setAge(personUpdateForm.getAge());
        person.setPhone(personUpdateForm.getPhone());

        System.out.println(personUpdateForm.getUserName() + " " + personUpdateForm.getPassword() + "" + personUpdateForm.getName());
    }
}
