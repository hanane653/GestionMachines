package gestionMachines;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import gestionMachines.ScaledImage;

@SuppressWarnings({ "serial", "unused" })
public class InterfaceAcceuil extends JFrame {
    public InterfaceAcceuil() {
        ScaledImage imageClose = new ScaledImage(InterfaceAcceuil.class.getResource("close.png"), 16, 16);
        ScaledImage imageClose1 = new ScaledImage(InterfaceAcceuil.class.getResource("administrateur.png"), 40, 40);
        ScaledImage imageClose2 = new ScaledImage(InterfaceAcceuil.class.getResource("utilisateur.jpg"), 40, 40);
        ScaledImage imageClose3 = new ScaledImage(InterfaceAcceuil.class.getResource("technicien.png"), 40, 40);

        setTitle("Page d'Acceuil");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);

        // Créez un JPanel pour le contenu de la fenêtre
        JPanel contentPanel = new JPanel(new BorderLayout());
        getContentPane().add(contentPanel);

        // Créez un JSplitPane pour diviser la fenêtre en deux parties
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        contentPanel.add(splitPane, BorderLayout.CENTER);

        // Créez un JPanel pour le menu à gauche
        JPanel menuPanel = new JPanel(new BorderLayout());
        menuPanel.setBackground(Color.WHITE);
        
        JMenuBar menuBar = createMenuBar(); // Méthode pour créer le menu

        // Ajouter le menu "a propos" dans le pied du panneau
        menuPanel.add(menuBar, BorderLayout.SOUTH);

        // Créez un JPanel pour les labels dans le haut du panneau
        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS));
        labelsPanel.setBackground(Color.WHITE);

        JPanel lblPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 0));
        lblPanel.setBackground(MyColor.WHITE);
        lblPanel.setForeground(MyColor.BLACK);
        lblPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lbl = new JLabel("identifiez-vous:");
        lblPanel.add(lbl);
        labelsPanel.add(lblPanel);
        
        JPanel adminPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        adminPanel.setBackground(MyColor.WHITE);
        adminPanel.add(new JLabel(imageClose1.icon));
        JLabel labelA = new JLabel("ADMINISTRATEUR");
        labelA.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	try {
            		Administrateur example = new Administrateur();
                    example.frame.setVisible(true);
				} catch (Exception E) {
					E.printStackTrace();
				}
                
            }
        });
        adminPanel.add(labelA);
        adminPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel utilisateurPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        utilisateurPanel.setBackground(MyColor.WHITE);
        utilisateurPanel.add(new JLabel(imageClose2.icon));
        JLabel labelU = new JLabel("UTILISATEUR");
        utilisateurPanel.add(labelU);
        labelU.addMouseListener(new MouseAdapter() {
        	
        	public void mouseClicked(MouseEvent e) {
            	try {
            		InterfaceUtilisateur windo = new InterfaceUtilisateur();
					windo.frame.setVisible(true);
				} catch (Exception E) {
					E.printStackTrace();
				}
                
            }
        	
		} );
        utilisateurPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        labelsPanel.add(adminPanel);
        labelsPanel.add(utilisateurPanel);

        JPanel technicienPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        technicienPanel.setBackground(MyColor.WHITE);
        technicienPanel.add(new JLabel(imageClose3.icon));
        JLabel labelT = new JLabel("TECHNICIEN");
        technicienPanel.add(labelT);
        labelT.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
            	try {
            		InterfaceTechnicien window = new InterfaceTechnicien();
					window.frame.setVisible(true);
				} catch (Exception E) {
					E.printStackTrace();
				}
                
            }
        	
        	
		});
        technicienPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        labelsPanel.add(technicienPanel);

        // Ajouter les labels dans le haut du panneau
        menuPanel.add(labelsPanel, BorderLayout.NORTH);

        splitPane.setLeftComponent(menuPanel);

        // Créez un JPanel pour l'image à droite
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setLayout(new BorderLayout());
        imagePanel.setBackground(MyColor.SECONDARY);
        ImageIcon backgroundImage = new ImageIcon("C:\\Users\\hp\\Documents\\radeef.jpg");
        Image originalImage = backgroundImage.getImage();

        int newWidth = 400;
        int newHeight = 350;
        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // Créer un nouveau ImageIcon à partir de l'image redimensionnée
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel backgroundImag = new JLabel(resizedIcon);
        imagePanel.add(backgroundImag, BorderLayout.CENTER);

       
        JLabel label = new JLabel("<html><div style='text-align: center;'>Bienvenue dans l'application de gestion des machines de la radeef</div></html>");
        label.setForeground(MyColor.WHITE);
        label.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 30));
       

        imagePanel.add(label, BorderLayout.CENTER);
        JPanel picPanel = new JPanel(new BorderLayout());
        picPanel.setBackground(MyColor.SECONDARY);

        picPanel.add(backgroundImag, BorderLayout.SOUTH);
        imagePanel.add(picPanel, BorderLayout.SOUTH);
        splitPane.setRightComponent(imagePanel);

        JPanel closePanel = new JPanel(new BorderLayout());
        closePanel.setBackground(MyColor.SECONDARY);
        closePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        closePanel.setPreferredSize(new Dimension(30, 30));
        JLabel lblClose = new JLabel("");
        lblClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to close this application?", "Confirmation", JOptionPane.WARNING_MESSAGE);

                if (confirmation == 0) {
                    System.exit(0);
                }
            }
        });
        lblClose.setHorizontalAlignment(SwingConstants.CENTER);
        lblClose.setBounds(567, 5, 24, 24);
        lblClose.setIcon(imageClose.icon);
        closePanel.add(lblClose, BorderLayout.EAST);
        imagePanel.add(closePanel, BorderLayout.NORTH);

        // Ajoutez le JPanel à la fenêtre principale
        add(contentPanel);
    }

    private JMenuBar createMenuBar() {
        ScaledImage icon = new ScaledImage(InterfaceUtilisateur.class.getResource("propos.png"), 18, 18);
        JLabel lblMenuIcon = new JLabel("");
        lblMenuIcon.setIcon(icon.icon);
        JMenuBar menuBar = new JMenuBar();
        JMenu mainMenu = new JMenu("a propos");
        mainMenu.setOpaque(true);
        mainMenu.setBackground(MyColor.SECONDARY);
        mainMenu.setForeground(MyColor.WHITE);
        mainMenu.setIcon(icon.icon);
        // Ajoutez le menu principal à la barre de menus
        menuBar.add(mainMenu, lblMenuIcon);

        return menuBar;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfaceAcceuil example = new InterfaceAcceuil();
            example.setVisible(true);
        });
    }
}
