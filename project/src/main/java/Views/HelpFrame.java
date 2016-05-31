package Views;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class HelpFrame extends JFrame{
	private String picFolderPath;
	private JLabel helpImage;
	private JButton PrevBtn;
	private JButton btnNext;
	private File[] imagesArr;
	public HelpFrame(String picFolderPath)
	{
		this.picFolderPath = picFolderPath;
		imagesArr = getImages();
		initView(900,635);
	}
	
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
	private File[] getImages()
	{
		File fileList = new File(getClass().getResource(picFolderPath).getFile());
		if(fileList.isDirectory())
		{
			return fileList.listFiles();
		}
		return null;	
	}
	public void setImage(int pos)
	{
		try {
			if(pos == 0)
				PrevBtn.setEnabled(false);
			else 
				PrevBtn.setEnabled(true);
			
			if(pos == imagesArr.length-1)
				btnNext.setEnabled(false);
			else
				btnNext.setEnabled(true);
			
			helpImage.setIcon(new ImageIcon(imagesArr[pos].getCanonicalPath()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addPrevBtnListener(ActionListener listener)
	{
		PrevBtn.addActionListener(listener);
	}
	public void addNextBtnListener(ActionListener listener)
	{
		btnNext.addActionListener(listener);
	}
	public File[] getImagesArr()
	{
		return imagesArr;
	}
	public void setImageSize(int width,int height)
	{
		helpImage.setMinimumSize(new Dimension(width, height));
		helpImage.setSize(new Dimension(width, height));
		pack();
	}
}
