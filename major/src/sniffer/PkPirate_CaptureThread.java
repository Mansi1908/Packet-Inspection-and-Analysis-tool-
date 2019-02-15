package sniffer;
import javax.swing.SwingUtilities;
public abstract class PkPirate_CaptureThread {

	private Object value;
	private Thread thread;
	
	private static class ThreadVar
	{
		private Thread thread;
		ThreadVar(Thread t)
		{
			thread = t;
		}
		synchronized Thread get()
		{
			return thread;
		}
		synchronized void clear()
		{
			thread=null;
		}
	}
		private ThreadVar threadvar;
		
		protected synchronized Object getValue()
		{
			return value;
		}
		private synchronized void setValue(Object x)
		{
			value=x;
		}
		
		public abstract Object construct();
		
		public void finished() {
		}
		public void interrupt()
		{
			Thread t=threadvar.get();
			
			if(t != null)
			{
				t.interrupt();
			}
			threadvar.clear();
		}
		public Object get()
		{
			while(true)
			{
				Thread t =threadvar.get();
				
				if(t == null)
				{
					return getValue();
				}
				try
				{
					t.join();
				}
				catch(InterruptedException e)
				{
					Thread.currentThread().interrupt();
					return null;
				}
				
			}
		}
		public PkPirate_CaptureThread()
		{
			final Runnable doFinished = new Runnable()
					{
						public void run()
						{
							finished();
						}
					};
					
			Runnable doConstruct = new Runnable()
					{

						@Override
						public void run() {
							// TODO Auto-generated method stub
							try
							{
								setValue(construct());
							}
							finally
							{
								threadvar.clear();
							}
							SwingUtilities.invokeLater(doFinished);
						}
				
					};
					
					Thread t = new Thread(doConstruct);
					threadvar= new ThreadVar(t);
		}
		public void start()
		{
			Thread t = threadvar.get();
			if(t!=null)
			{
				t.start();
			}
		}
}
