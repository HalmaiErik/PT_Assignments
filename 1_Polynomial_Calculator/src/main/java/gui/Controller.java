package gui;

import polynomial.Operations;
import polynomial.Polynomial;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        view.addDerivateListener(new DerivateListener());
        view.addIntegrateListener(new IntegrateListener());
        view.addAddListener(new AddListener());
        view.addSubtractListener(new SubtractListener());
        view.addMultiplyListener(new MultiplyListener());
        view.addDivideListener(new DivideListener());
        view.addClearListener(new ClearListener());
    }

    class DerivateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String inputString = "";
            try {
                if(e.getSource() == view.getDerivateBtn1())
                    inputString = view.getInputOne();
                else if (e.getSource() == view.getDerivateBtn2())
                    inputString = view.getInputTwo();

                Polynomial polynomial;
                if(inputString.equals(""))
                    polynomial = null;
                else
                    polynomial = new Polynomial(model.checkPolynomial(inputString));

                Polynomial derivative = Operations.derivative(polynomial);
                view.setResult(derivative.toString());
            }
            catch (Exception ex) {
                view.showError(ex);
            }
        }
    }

    class IntegrateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String inputString = "";
            try {
                if(e.getSource() == view.getIntegrateBtn1())
                    inputString = view.getInputOne();
                else if (e.getSource() == view.getIntegrateBtn2())
                    inputString = view.getInputTwo();

                Polynomial polynomial;
                if(inputString.equals(""))
                    polynomial = null;
                else
                    polynomial = new Polynomial(model.checkPolynomial(inputString));

                Polynomial integral = Operations.integration(polynomial);
                view.setResult(integral.toString());
            }
            catch (Exception ex) {
                view.showError(ex);
            }
        }
    }

    class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String inputString1 = "";
            String inputString2 = "";
            try {
                inputString1 = view.getInputOne();
                Polynomial polynomial1;
                if(inputString1.equals(""))
                    polynomial1 = null;
                else
                    polynomial1 = new Polynomial(model.checkPolynomial(inputString1));

                inputString2 = view.getInputTwo();
                Polynomial polynomial2;
                if(inputString2.equals(""))
                    polynomial2 = null;
                else
                    polynomial2 = new Polynomial(model.checkPolynomial(inputString2));

                Polynomial addition = Operations.additionOrSubtraction(polynomial1, polynomial2, '+');
                view.setResult(addition.toString());
            }
            catch (Exception ex) {
                view.showError(ex);
            }
        }
    }

    class SubtractListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String inputString1 = "";
            String inputString2 = "";
            try {
                inputString1 = view.getInputOne();
                Polynomial polynomial1;
                if(inputString1.equals(""))
                    polynomial1 = null;
                else
                    polynomial1 = new Polynomial(model.checkPolynomial(inputString1));

                inputString2 = view.getInputTwo();
                Polynomial polynomial2;
                if(inputString2.equals(""))
                    polynomial2 = null;
                else
                    polynomial2 = new Polynomial(model.checkPolynomial(inputString2));

                Polynomial subtraction = Operations.additionOrSubtraction(polynomial1, polynomial2, '-');
                view.setResult(subtraction.toString());
            }
            catch (Exception ex) {
                view.showError(ex);
            }
        }
    }

    class MultiplyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String inputString1 = "";
            String inputString2 = "";
            try {
                inputString1 = view.getInputOne();
                Polynomial polynomial1;
                if(inputString1.equals(""))
                    polynomial1 = null;
                else
                    polynomial1 = new Polynomial(model.checkPolynomial(inputString1));

                inputString2 = view.getInputTwo();
                Polynomial polynomial2;
                if(inputString2.equals(""))
                    polynomial2 = null;
                else
                    polynomial2 = new Polynomial(model.checkPolynomial(inputString2));

                Polynomial multiplication = Operations.multiplication(polynomial1, polynomial2);
                view.setResult(multiplication.toString());
            }
            catch (Exception ex) {
                view.showError(ex);
            }
        }
    }

    class DivideListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String inputString1 = "";
            String inputString2 = "";
            try {
                inputString1 = view.getInputOne();
                Polynomial polynomial1;
                if(inputString1.equals(""))
                    polynomial1 = null;
                else
                    polynomial1 = new Polynomial(model.checkPolynomial(inputString1));

                inputString2 = view.getInputTwo();
                Polynomial polynomial2;
                if(inputString2.equals(""))
                    polynomial2 = null;
                else
                    polynomial2 = new Polynomial(model.checkPolynomial(inputString2));

                LinkedList<Polynomial> divison = (LinkedList<Polynomial>) Operations.division(polynomial1, polynomial2);
                String result = divison.get(0).toString() + " + " + divison.get(1).toString() +
                                " / (" + polynomial2.toString() + ")";
                view.setResult(result);
            }
            catch (Exception ex) {
                view.showError(ex);
            }
        }
    }

    class ClearListener implements  ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.resetAll();
        }
    }
}
