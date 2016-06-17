package Views;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import java.awt.Color;
import javax.swing.border.LineBorder;

/**
 * The Class HelpFrame.
 * This class is used for presenting the help screens.
 */
public class HelpFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The pic path. */
	private String picPath;
	
	/** The amount of pictures. */
	private int amountOfPictures;
	
	/** The help image. */
	private JLabel helpImage;
	
	/** The Previous button. */
	private JButton PrevBtn;
	
	/** The button next. */
	private JButton btnNext;
	
	/** The images array. */
	private URL[] imagesArr;
	
	/**
	 * Instantiates a new help frame.
	 *
	 * @param picPath the pic path
	 * @param amountOfPictures the amount of pictures
	 */
	public HelpFrame(String picPath,int amountOfPictures)
	{
		this.picPath = picPath;
		this.amountOfPictures = amountOfPictures;
		imagesArr = getImages();
		initView(900,635);
	}
	
	/**
	 * Inits the view.
	 *
	 * @param width the width
	 * @param height the height
	 */
	private void initView(int width,int height) {
		// TODO Auto-generated method stub
		setTitle("Help");
		
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel picturePanel = new JPanel();
		picturePanel.setBackground(Color.WHITE);
		picturePanel.setMinimumSize(new Dimension(width, height));
		picturePanel.setPreferredSize(new Dimension(width, height));
		panel.add(picturePanel);
		
		helpImage = new JLabel();
		helpImage.setMinimumSize(new Dimension(width, height));
		helpImage.setSize(new Dimension(width, height));
		
		picturePanel.add(helpImage);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBackground(Color.LIGHT_GRAY);
		buttonsPanel.setMaximumSize(new Dimension(32767, 100));
		panel.add(buttonsPanel);
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setOpaque(false);
		buttonsPanel.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		PrevBtn = new JButton("Previous");
		PrevBtn.setMinimumSize(new Dimension(73, 40));
		PrevBtn.setMaximumSize(new Dimension(600, 40));
		panel_1.add(PrevBtn);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setOpaque(false);
		buttonsPanel.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		btnNext = new JButton("Next");
		btnNext.setMinimumSize(new Dimension(55, 40));
		btnNext.setMaximumSize(new Dimension(600, 40));
		panel_2.add(btnNext);
//		btnNext.setPreferredSize(new Dimension(73, 23));
		setImage(0);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	/**
	 * Gets the images.
	 *
	 * @return the images
	 */
	private URL[] getImages()
	{
		URL []urlList = new URL[amountOfPictures];
		for(int i=0;i<amountOfPictures;i++)
		{
			urlList[i]= getClass().getResource("/"+picPath+(i+1)+".png");
		}
		return urlList;	
	}
	
	/**
	 * Sets the image.
	 *
	 * @param pos the new image
	 */
	public void setImage(int pos)
	{
		if(pos == 0)
			PrevBtn.setEnabled(false);
		else 
			PrevBtn.setEnabled(true);
		
		if(pos == imagesArr.length-1)
			btnNext.setEnabled(false);
		else
			btnNext.setEnabled(true);
		
		helpImage.setIcon(new ImageIcon(imagesArr[pos]));
	}
	
	/**
	 * Adds the prev btn listener.
	 *
	 * @param listener the listener
	 */
	public void addPrevBtnListener(ActionListener listener)
	{
		PrevBtn.addActionListener(listener);
	}
	
	/**
	 * Adds the next btn listener.
	 *
	 * @param listener the listener
	 */
	public void addNextBtnListener(ActionListener listener)
	{
		btnNext.addActionListener(listener);
	}
	
	/**
	 * Gets the images arr.
	 *
	 * @return the images arr
	 */
	public URL[] getImagesArr()
	{
		return imagesArr;
	}
	
	/**
	 * Sets the image size.
	 *
	 * @param width the width
	 * @param height the height
	 */
	public void setImageSize(int width,int height)
	{
		helpImage.setMinimumSize(new Dimension(width, height));
		helpImage.setSize(new Dimension(width, height));
		pack();
	}
}
