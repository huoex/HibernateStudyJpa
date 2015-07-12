package ua.anakin.model_12_many_to_one;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import util.JPAUtil;

@SuppressWarnings("unchecked")
public class MainFor12 {
	static EntityManager entityManager;
    public static void main(String[] args) {
    	
    	EntityManagerFactory factory = JPAUtil.getEntityManagerFactory();
    	entityManager = factory.createEntityManager();
    	MainFor12 m = new MainFor12();
    	
//    	List<Receita>  lst = m.findReceitasByUsuario(1L);
//    	System.out.println(lst.size());
    	
    	m.findReceitasByTitulo("");
    }
    

	public List<Receita> findReceitasByUsuario(Long usuarioId) {
		Query query = entityManager.createQuery("select r from Receita r where usuario_id = :usuarioId");
		query.setParameter("usuarioId", usuarioId);
		
		return query.getResultList();
	}

	public List<Receita> findReceitasByTitulo(String titulo) {
		StringBuffer qry = new StringBuffer("select r from Receita r ");
		qry.append("left join r.usuario u ");
		if(titulo != null && !"".equals(titulo))
			qry.append("where upper(r.titulo) like '%" + titulo.toUpperCase() +"%' ");
		qry.append("order by r.id desc ");
		
		Query query = entityManager.createQuery(qry.toString());
		return query.getResultList();
	}

	public Receita findById(Long receitaId) {
		Query query = entityManager.createQuery("select r from Receita r where r.id = :receitaId");
		query.setParameter("receitaId", receitaId);

		return (Receita) query.getSingleResult();
	}
}