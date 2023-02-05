package application.search;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TextField;
import objects.dynasty.Dynasty;
import objects.figure.King;

public class Search<T> {
    public ObservableList<T> demoSearch(ObservableList<T> data, TextField textField, Class<T> type) {
        FilteredList<T> filteredData = new FilteredList<>(data, b -> true);
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(obj -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                Method[] methods = type.getMethods();
                for (Method m : methods) {
                    if (m.getName().startsWith("get")) {
                        // System.out.println(String.class);
                        try {
                            if (m.getReturnType() == String.class) {
                                try {
                                    String objField;
                                    objField = (String) m.invoke(obj);
                                    if (objField == null) {
                                        continue;
                                    } else if (objField.toLowerCase().indexOf(lowerCaseFilter) != -1) {
                                        return true;
                                    } else {
                                        continue;
                                    }
                                    // if (lowerCaseFilter.indexOf(objField) != -1) {
                                    // return true;
                                    // }
                                } catch (IllegalAccessException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                } catch (IllegalArgumentException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                } catch (InvocationTargetException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }

                            } else if (m.getReturnType() == ArrayList.class) {
                                try {
                                    ArrayList<Dynasty> objField;
                                    objField = (ArrayList<Dynasty>) m.invoke(obj);
                                    for (int i = 0; i < objField.size(); i++) {
                                        if (objField == null) {
                                            continue;
                                        } else if (objField.get(i).getName().toLowerCase()
                                                .indexOf(lowerCaseFilter) != -1) {
                                            // System.out.println(objField.get(i).getName());
                                            return true;
                                        } else {
                                            continue;
                                        }
                                    }

                                    // if (lowerCaseFilter.indexOf(objField) != -1) {
                                    // return true;
                                    // }
                                } catch (IllegalAccessException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                } catch (IllegalArgumentException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                } catch (InvocationTargetException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }

                            } else if (m.getReturnType() == LinkedList.class) {
                                try {
                                    LinkedList<King> objField;
                                    objField = (LinkedList<King>) m.invoke(obj);
                                    for (int i = 0; i < objField.size(); i++) {
                                        if (objField == null) {
                                            continue;
                                        } else if (objField.get(i).getTen().toLowerCase()
                                                .indexOf(lowerCaseFilter) != -1) {
                                            return true;
                                        } else {
                                            continue;
                                        }
                                    }

                                    // if (lowerCaseFilter.indexOf(objField) != -1) {
                                    // return true;
                                    // }
                                } catch (IllegalAccessException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                } catch (IllegalArgumentException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                } catch (InvocationTargetException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }

                            } else {
                                continue;
                            }

                        } catch (ArithmeticException e) {
                            System.out.println("Error!");
                        }
                    }
                }
                return false;
            });
        });
        return filteredData;
    }
}
