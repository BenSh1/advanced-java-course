
public class Worker implements Runnable{
	private ResourcePool rPool;
	
	public Worker(ResourcePool rPool) {
		// TODO Auto-generated constructor stub
		this.rPool = rPool;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Resource r =  rPool.getResource();
		System.out.println(Thread.currentThread().getName() +" get the resource of the id : "+r.getId());
		r.use();
		rPool.returnResource(r);
		
	}

}
