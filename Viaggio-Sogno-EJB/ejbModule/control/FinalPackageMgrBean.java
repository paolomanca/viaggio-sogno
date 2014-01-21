package control;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.FinalPackage;
import model.Group;
import dto.FinalPackageDTO;
import dto.PackageDTO;
import entitymanagers.FinalPackageMgr;

public class FinalPackageMgrBean implements FinalPackageMgr {

	@PersistenceContext
	private EntityManager em;

	@Resource
	private EJBContext context;

	@EJB
	private UserMgrBean usrMgr;
	
	@Override
	@RolesAllowed({Group._CUSTOMER})
	public void add(FinalPackageDTO finalPkgDTO) {
		FinalPackage newFPkg = new FinalPackage(finalPkgDTO);
		newFPkg.setUser(usrMgr.findByEmail(context.getCallerPrincipal().getName()));
		em.persist(newFPkg);
	}

	@Override
	public void update(FinalPackageDTO finalPkgDTO) {
		FinalPackage newFPkg = new FinalPackage(finalPkgDTO);
		em.merge(finalPkgDTO);
	}

	@Override
	public void remove(FinalPackageDTO finalPkgDTO) {
		em.remove(new FinalPackage(finalPkgDTO));
	}

}
