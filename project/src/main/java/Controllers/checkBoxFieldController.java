package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;
import Views.checkBoxFieldPanel;

/**
 * The Class checkBoxFieldController.
 * This class is a controller of a component consists of a JCheckBox ,JTextField and "+","-" buttons.
 */
public class checkBoxFieldController implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The view. */
	public checkBoxFieldPanel view;
	
	/** The parent controller. */
	private MultipleChoicePanelController parentController;

	/**
	 * Instantiates a new check box field controller.
	 *
	 * @param view the view
	 * @param parentController the parent controller
	 */
	public checkBoxFieldController(checkBoxFieldPanel view, MultipleChoicePanelController parentController) {
		this.view = view;
		this.parentController = parentController;
		this.view.plusBtnAddListener(new plusBtnListener());
		this.view.minusBtnAddListener(new minusBtnListener());
		this.view.addAnswerTextOptionKeyListener(new answerTextOptionkeyListener());
		this.view.addAnswerCheckBoxListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				QuizCreationController.saveFlag = 0;
			}
		});
	}

	/**
	 * The listener interface for receiving checkbox clicking events.
	 * The class that is interested in processing a answerTextOptionkey
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addanswerTextOptionkeyListener<code> method. When
	 * the answerTextOptionkey event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see answerTextOptionkeyEvent
	 */
	class answerTextOptionkeyListener implements KeyListener, Serializable {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/* (non-Javadoc)
		 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
		 */
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		/* (non-Javadoc)
		 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
		 */
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		/* (non-Javadoc)
		 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
		 */
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			QuizCreationController.saveFlag = 0;
		}

	}

	/**
	 * The listener interface for receiving plusBtn events.
	 * The class that is interested in processing a plusBtn
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addplusBtnListener<code> method. When
	 * the plusBtn event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see plusBtnEvent
	 */
	class plusBtnListener implements ActionListener, Serializable {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			addCheckBoxField();
			parentController.view.panel.revalidate();
		}

		/**
		 * Adds a check box field to the parent panel (adds another option).
		 */
		public void addCheckBoxField() {
			checkBoxFieldPanel checkBoxFieldPanel = new checkBoxFieldPanel();
			checkBoxFieldController checkBoxFieldController = new checkBoxFieldController(checkBoxFieldPanel,
					parentController);
			parentController.view.panel.add(checkBoxFieldPanel);
			parentController.cBfControllers.add(checkBoxFieldController);
			view.getMinusBtn().setVisible(true);
			view.getPlusBtn().setVisible(false);

			QuizCreationController.saveFlag = 0;
			checkBoxFieldPanel.setAnswerNumber(parentController.cBfControllers.size());
			checkBoxFieldPanel.setaNumberLbl(parentController.cBfControllers.size() + ".");
		}
	}

	/**
	 * The listener interface for receiving minusBtn events.
	 * The class that is interested in processing a minusBtn
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addminusBtnListener<code> method. When
	 * the minusBtn event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see minusBtnEvent
	 */
	class minusBtnListener implements ActionListener, Serializable {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/** Removes a checkBoxField from parent panel */
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			parentController.view.panel.remove(view);
			parentController.cBfControllers.remove(view.getAnswerNumber() - 1);
			rebuildAnswersPanel();

			QuizCreationController.saveFlag = 0;
			/*
			 * if(temp.getAnswerNumber()==1)
			 * temp.getMinusBtn().setVisible(false);
			 * temp.getPlusBtn().setVisible(true);
			 */
			parentController.view.panel.revalidate();
			MainFrameController.view.repaint();
		}

		/**
		 * Rebuild answers panel.
		 */
		public void rebuildAnswersPanel() {

			for (int i = 0; i < parentController.cBfControllers.size(); i++) {

				parentController.cBfControllers.get(i).view.setAnswerNumber(i + 1);
				parentController.cBfControllers.get(i).view.setaNumberLbl((i + 1) + ".");
				if (i == parentController.cBfControllers.size() - 1) {
					parentController.cBfControllers.get(i).view.getPlusBtn().setVisible(true);
					parentController.cBfControllers.get(i).view.getMinusBtn().setVisible(true);
				}
				if (i == 0 && parentController.cBfControllers.size() == 1) {
					parentController.cBfControllers.get(i).view.getPlusBtn().setVisible(true);
					parentController.cBfControllers.get(i).view.getMinusBtn().setVisible(false);
				}

			}
		}
	}
}
