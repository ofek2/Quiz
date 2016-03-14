package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;
import Views.checkBoxFieldPanel;

public class checkBoxFieldController implements Serializable {
	public checkBoxFieldPanel view;
	private MultipleChoicePanelController parentController;

	public checkBoxFieldController(checkBoxFieldPanel view, MultipleChoicePanelController parentController) {
		this.view = view;
		this.parentController = parentController;
		this.view.plusBtnAddListener(new plusBtnListener());
		this.view.minusBtnAddListener(new minusBtnListener());
		this.view.addAnswerTextOptionKeyListener(new answerTextOptionkeyListener());
		this.view.addChckbxNewCheckBox(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				QuizCreationController.saveFlag = 0;
			}
		});
	}

	class answerTextOptionkeyListener implements KeyListener, Serializable {

		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			QuizCreationController.saveFlag = 0;
		}

	}

	class plusBtnListener implements ActionListener, Serializable {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			addCheckBoxField();
			parentController.view.panel.revalidate();
		}

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

	class minusBtnListener implements ActionListener, Serializable {

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
