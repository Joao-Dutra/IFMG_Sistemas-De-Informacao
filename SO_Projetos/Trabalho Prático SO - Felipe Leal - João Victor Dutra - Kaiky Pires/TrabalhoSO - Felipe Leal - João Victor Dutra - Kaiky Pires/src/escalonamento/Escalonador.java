package escalonamento;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import java.util.List;

public class Escalonador {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> criarInterfaceGrafica());
    }

    private static void criarInterfaceGrafica() {
        JFrame frame = new JFrame("Simulador de Escalonamento de Processos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 900);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        logoPanel.setBorder(BorderFactory.createEmptyBorder(1, 10, 1, 10));
        logoPanel.setBackground(Color.WHITE);

        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = new ImageIcon("assets/ifmg - logo.png");
        Image logoImage = logoIcon.getImage().getScaledInstance(100, 50, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(logoImage));
        logoPanel.add(logoLabel);

        mainPanel.add(logoPanel, BorderLayout.NORTH);

        JPanel simuladorPanel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        int maxThreads = Runtime.getRuntime().availableProcessors();
        Integer[] threadOptions = new Integer[maxThreads];
        for (int i = 0; i < maxThreads; i++) {
            threadOptions[i] = i + 1;
        }
        JComboBox<Integer> numProcessadoresDropdown = new JComboBox<>(threadOptions);
        JTextField numProcessosField = new JTextField(10);
        JTextField quantumField = new JTextField(10);

        inputPanel.add(new JLabel("Numero de Processadores:"));
        inputPanel.add(numProcessadoresDropdown);
        inputPanel.add(new JLabel("Numero de Processos na Fila de Prontos:"));
        inputPanel.add(numProcessosField);
        inputPanel.add(new JLabel("Quantum (para Round-Robin):"));
        inputPanel.add(quantumField);

        JButton iniciarButton = new JButton("Iniciar Simulacao");
        inputPanel.add(iniciarButton);
        iniciarButton.setBackground(new Color(0xE30613));
        iniciarButton.setForeground(Color.WHITE);
        iniciarButton.setFont(new Font("Arial", Font.BOLD, 14));
        simuladorPanel.add(inputPanel, BorderLayout.NORTH);

        JPanel botaoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        botaoPanel.add(iniciarButton);
        botaoPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        inputPanel.add(botaoPanel);

        JTextArea outputArea = new JTextArea(20, 50);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        simuladorPanel.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(0x009639), 2));

        JPanel bottomPanel = new JPanel(new BorderLayout());

        JPanel graficoPanel = new JPanel();
        graficoPanel.setPreferredSize(new Dimension(1000, 300));
        graficoPanel.setBorder(BorderFactory.createLineBorder(new Color(0x009639), 2));
        JScrollPane scrollPaneGrafico = new JScrollPane(graficoPanel);
        bottomPanel.add(scrollPaneGrafico, BorderLayout.NORTH);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Indicadores");
        tableModel.addColumn("Valores");
        tableModel.addColumn("Descricao");
        JTable tabelaIndicadores = new JTable(tableModel);
        JScrollPane scrollPaneTabela = new JScrollPane(tabelaIndicadores);
        scrollPaneTabela.setPreferredSize(new Dimension(1000, 150));
        bottomPanel.add(scrollPaneTabela, BorderLayout.CENTER);
        JTableHeader cabecalho = tabelaIndicadores.getTableHeader();
        cabecalho.setBackground(new Color(0x009639));
        cabecalho.setForeground(Color.WHITE);
        cabecalho.setFont(new Font("Arial", Font.BOLD, 16));
        cabecalho.setBorder(BorderFactory.createLineBorder(new Color(0x009639), 1));
        simuladorPanel.add(bottomPanel, BorderLayout.SOUTH);

        tabbedPane.add("Simulacao", simuladorPanel);

        JPanel processosPanel = new JPanel(new BorderLayout());
        DefaultTableModel processosTableModel = new DefaultTableModel();
        processosTableModel.addColumn("ID");
        processosTableModel.addColumn("Chegada");
        processosTableModel.addColumn("Duracao");

        JTable tabelaProcessos = new JTable(processosTableModel);
        JScrollPane scrollPaneProcessos = new JScrollPane(tabelaProcessos);
        scrollPaneProcessos.setBorder(BorderFactory.createLineBorder(new Color(0x009639), 2));
        JPanel addProcessoPanel = new JPanel();
        JTextField chegadaField = new JTextField(5);
        JTextField duracaoField = new JTextField(5);
        JButton addProcessoButton = new JButton("Adicionar Processo");

        addProcessoPanel.add(new JLabel("Chegada:"));
        addProcessoPanel.add(chegadaField);
        addProcessoPanel.add(new JLabel("Duracao:"));
        addProcessoPanel.add(duracaoField);
        addProcessoPanel.add(addProcessoButton);

        processosPanel.add(scrollPaneProcessos, BorderLayout.CENTER);
        processosPanel.add(addProcessoPanel, BorderLayout.SOUTH);

        tabbedPane.add("Criar Processos", processosPanel);

        addProcessoButton.addActionListener(e -> {
            try {
                int chegada = Integer.parseInt(chegadaField.getText());
                int duracao = Integer.parseInt(duracaoField.getText());
                int id = processosTableModel.getRowCount() + 1;
                processosTableModel.addRow(new Object[] { id, chegada, duracao });
                chegadaField.setText("");
                duracaoField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Por favor, insira valores válidos para o processo.");
            }
        });

        JPanel historicoPanel = new JPanel(new BorderLayout());
        DefaultTableModel historicoTableModel = new DefaultTableModel();
        historicoTableModel.addColumn("Numero de Processadores");
        historicoTableModel.addColumn("Processos");
        historicoTableModel.addColumn("Quantum");
        historicoTableModel.addColumn("Tempo Medio de Execucao (RR)");
        historicoTableModel.addColumn("Tempo Medio de Espera (RR)");
        historicoTableModel.addColumn("Trocas de Contexto (RR)");
        historicoTableModel.addColumn("Tempo Medio de Execucao (SJF)");
        historicoTableModel.addColumn("Tempo Medio de Espera (SJF)");

        JTable tabelaHistorico = new JTable(historicoTableModel);
        JScrollPane scrollPaneHistorico = new JScrollPane(tabelaHistorico);
        historicoPanel.add(scrollPaneHistorico, BorderLayout.CENTER);

        tabbedPane.add("Historico", historicoPanel);

        iniciarButton.addActionListener(e -> {
            try {
                int numProcessadores = (int) numProcessadoresDropdown.getSelectedItem();
                int numProcessos = Integer.parseInt(numProcessosField.getText());
                int quantum = Integer.parseInt(quantumField.getText());

                List<Processo> processosCriados = new ArrayList<>();
                for (int i = 0; i < processosTableModel.getRowCount(); i++) {
                    int id = Integer.parseInt(processosTableModel.getValueAt(i, 0).toString());
                    int chegada = Integer.parseInt(processosTableModel.getValueAt(i, 1).toString());
                    int duracao = Integer.parseInt(processosTableModel.getValueAt(i, 2).toString());
                    processosCriados.add(new Processo(id, chegada, duracao));
                }

                Simulador simulador = new Simulador(numProcessadores, quantum,
                        processosCriados.isEmpty() ? null : processosCriados, numProcessos);
                String resultado = simulador.executar();
                outputArea.setText(resultado);

                DefaultCategoryDataset dataset = simulador.getDataset();
                JFreeChart chart = ChartFactory.createBarChart(
                        "Desempenho dos Algoritmos",
                        "Indicadores",
                        "Tempo (s)",
                        dataset);

                CategoryPlot plot = chart.getCategoryPlot();
                BarRenderer renderer = (BarRenderer) plot.getRenderer();

                renderer.setSeriesPaint(0, new Color(0xB22222));
                renderer.setSeriesPaint(1, new Color(0x009639));

                ChartPanel chartPanel = new ChartPanel(chart);
                chartPanel.setPreferredSize(new Dimension(1000, 300));
                graficoPanel.removeAll();
                graficoPanel.add(chartPanel);
                graficoPanel.revalidate();
                chartPanel.setPreferredSize(new Dimension(900, 280));

                tableModel.setRowCount(0);
                tableModel.addRow(new Object[] { "Tempo Medio de Execucao (RR)", simulador.getTurnaroundTimeRR(),
                        "Tempo medio de execucao no Round-Robin" });
                tableModel.addRow(new Object[] { "Tempo Medio de Espera (RR)", simulador.getWaitingTimeRR(),
                        "Tempo medio de espera no Round-Robin" });
                tableModel.addRow(new Object[] { "Trocas de Contexto (RR)", simulador.getContextSwitchesRR(),
                        "Trocas de contexto no Round-Robin" });
                tableModel.addRow(new Object[] { "Tempo Medio de Execucao (SJF)", simulador.getTurnaroundTimeSJF(),
                        "Tempo medio de execucao no SJF" });
                tableModel.addRow(new Object[] { "Tempo Medio de Espera (SJF)", simulador.getWaitingTimeSJF(),
                        "Tempo medio de espera no SJF" });

                historicoTableModel.addRow(new Object[] {
                        numProcessadores,
                        numProcessos,
                        quantum,
                        simulador.getTurnaroundTimeRR(),
                        simulador.getWaitingTimeRR(),
                        simulador.getContextSwitchesRR(),
                        simulador.getTurnaroundTimeSJF(),
                        simulador.getWaitingTimeSJF()
                });

            } catch (NumberFormatException ex) {
                outputArea.setText("Por favor, insira valores válidos para os campos.");
            }
        });

        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
