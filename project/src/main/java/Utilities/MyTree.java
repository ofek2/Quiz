package Utilities;

import java.awt.*;
import java.awt.geom.*;
import java.io.File;

import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.tree.*;

import Entities.Constants;
import Views.InitialWindowView;

/**
 * The Class MyTree.
 * This class is used for creating the tree of folders in {@link InitialWindowView}.
 */
public class MyTree {
	
	/** The d64. */
	private static Dimension d64 = new Dimension(64, 64);
	
	/** The d32. */
	private static Dimension d32 = new Dimension(32, 32);
	
	/** The d24. */
	private static Dimension d24 = new Dimension(24, 24);
	
	/** The First node. */
	private static DefaultMutableTreeNode FirstNode;
	
	/**
	 * Make ui.
	 *
	 * @return the j tree
	 */
	public JTree makeUI() {

		IconUIResource emptyIcon = new IconUIResource(new Icon() {
			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) {
			}

			@Override
			public int getIconWidth() {
				return 0;
			}

			@Override
			public int getIconHeight() {
				return 0;
			}
		});
		UIManager.put("Tree.expandedIcon", emptyIcon);
		UIManager.put("Tree.collapsedIcon", emptyIcon);
		UIManager.put("Tree.paintLines", Boolean.FALSE);

		UIDefaults d = new UIDefaults();
		d.put("Tree:TreeCell[Enabled+Selected].backgroundPainter", new Painter<JComponent>() {
			@Override
			public void paint(Graphics2D g, JComponent c, int w, int h) {
				g.setPaint(Color.GREEN.darker());
				g.fillRect(0, 0, w, h);
			}
		});

		JTree tree = new JTree(makeModel(Constants.ROOTPATH));
		tree.expandPath(new TreePath(FirstNode.getPath()));
		tree.setCellRenderer(new TestTreeCellRenderer());
		tree.setFont(new Font("Arial", Font.PLAIN, 20));
		
		tree.setRootVisible(false);
		tree.setShowsRootHandles(false);
		tree.setBackground(Color.WHITE);
		tree.putClientProperty("Nimbus.Overrides", d);
		tree.putClientProperty("Nimbus.Overrides.InheritDefaults", false);

		return tree;
	}

	/**
	 * Make model.
	 *
	 * @param path the path
	 * @return the tree model
	 */
	private static TreeModel makeModel(String path) {

		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
		FirstNode = new DefaultMutableTreeNode(
				new TestNode("Courses", Color.BLUE.brighter().brighter(), d64, true));
		root.add(FirstNode); // add current folder to the tree
		File file = new File(path);
		addNodes(FirstNode, 0, file);
		
		return new DefaultTreeModel(root);
	}

	/**
	 * Adds the nodes.
	 *
	 * @param root the root
	 * @param level the level
	 * @param file the file
	 */
	private static void addNodes(DefaultMutableTreeNode root, int level, File file) {
		// TODO Auto-generated method stub
		if (!file.exists())
			return;
		switch (level) {
		case 0:
			if (file.isDirectory()) {
				for (int i = 0; i < file.listFiles().length; i++) {
					String name = file.listFiles()[i].getName();
					DefaultMutableTreeNode set1 = new DefaultMutableTreeNode(
							new TestNode(name, Color.ORANGE, d64, true));
					root.add(set1); // add current folder to the tree
					addNodes(set1, level + 1, file.listFiles()[i]); // add next
																	// file/folder
																	// to the
																	// tree
				}
			}
			break;
		case 1:
			if (file.isDirectory()) {
				for (int i = 0; i < file.listFiles().length; i++) {
					String name = file.listFiles()[i].getName();
					if (name.endsWith(".ser"))
						name = name.split("\\.")[0];
					if(!name.endsWith(".xls"))
					{
					DefaultMutableTreeNode set1 = new DefaultMutableTreeNode(
							new TestNode(name, Color.GREEN, d32, true));
					root.add(set1); // add current folder to the tree

					addNodes(set1, level + 1, file.listFiles()[i]); // add next
																	// file/folder
																	// to the
																	// tree
					}
				}
			}
			break;
		case 2:
			if (file.isDirectory()) {
				for (int i = 0; i < file.listFiles().length; i++) {
					String name = file.listFiles()[i].getName();
					if(name.endsWith(".ser"))
						name = name.split("\\.")[0];
					DefaultMutableTreeNode set1 = new DefaultMutableTreeNode(
							new TestNode(name, Color.BLUE, d24, true));
					root.add(set1); // add current folder to the tree
					
					addNodes(set1, level + 1, file.listFiles()[i]); // add next
																	// file/folder
																	// to the
																	// tree
					
				}
			}
			break;
		}

	}

	
}

class TestTreeCellRenderer extends DefaultTreeCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
			boolean leaf, int row, boolean hasFocus) {
		setBackgroundSelectionColor(Color.green.darker());
		JLabel l = (JLabel) super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
		if (value instanceof DefaultMutableTreeNode) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
			Object uo = node.getUserObject();
			if (uo instanceof TestNode) {
				TestNode i = (TestNode) uo;
				l.setForeground(Color.BLACK);
				l.setIcon(new TestNode(i.title, i.color, i.dim, leaf, expanded));

				int indent = 0;
				TreeNode parent = node.getParent();
				while (parent instanceof DefaultMutableTreeNode) {
					DefaultMutableTreeNode pn = (DefaultMutableTreeNode) parent;
					if (pn.getUserObject() instanceof TestNode) {
					}
					parent = pn.getParent();
				}
				l.setBorder(BorderFactory.createEmptyBorder(2, indent, 2, 5));
			}
		}
		return l;
	}
}

class TestNode implements Icon {
	public final String title;
	public final Color color;
	public final Dimension dim;
	private final boolean expanded;
	private final boolean leaf;
	private static int GAP = 4;

	// public TestNode(String title, ImageIcon img, Dimension dim) {
	public TestNode(String title, Color color, Dimension dim, boolean leaf) {
		this(title, color, dim, leaf, false);
	}

	public TestNode(String title, Color color, Dimension dim, boolean leaf, boolean expanded) {
		this.title = title;
		this.color = color;
		this.dim = dim;
		this.expanded = expanded;
		this.leaf = leaf;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		g.setColor(color);
		g.fillRect(x + GAP, y + GAP, dim.width - GAP - GAP, dim.height - GAP - GAP);
		if (dim.width > 24) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			int w6 = dim.width / 12;
			int w3 = dim.width / 6;
			g2.setColor(Color.WHITE);
			g2.setStroke(new BasicStroke(w6));
			Point pt = new Point(x + dim.width / 2, y + dim.height / 2);
			Path2D path = new Path2D.Double();
			path.moveTo(pt.x - w6, pt.y - w3);
			path.lineTo(pt.x + w6, pt.y);
			path.lineTo(pt.x - w6, pt.y + w3);
			int numquadrants;
			if (leaf) {
				numquadrants = 0;
			} else if (expanded) {
				numquadrants = 3;
			} else {
				numquadrants = 1;
			}
			AffineTransform at = AffineTransform.getQuadrantRotateInstance(numquadrants, pt.x, pt.y);
			g2.draw(at.createTransformedShape(path));
			g2.dispose();
		}
	}

	@Override
	public int getIconWidth() {
		return dim.width;
	}

	@Override
	public int getIconHeight() {
		return dim.height;
	}

	@Override
	public String toString() {
		return title;
	}
}