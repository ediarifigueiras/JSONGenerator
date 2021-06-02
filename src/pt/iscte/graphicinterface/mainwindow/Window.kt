package pt.iscte.graphicinterface.mainwindow

import org.eclipse.swt.SWT
import org.eclipse.swt.events.ModifyEvent
import org.eclipse.swt.events.ModifyListener
import org.eclipse.swt.events.SelectionAdapter
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.graphics.Color
import org.eclipse.swt.graphics.Image
import org.eclipse.swt.graphics.RGBA
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.layout.RowLayout
import org.eclipse.swt.widgets.*
import org.eclipse.swt.widgets.Composite
import pt.iscte.datamodel.*
import pt.iscte.datamodel.visitor.JSONSerializeVisitor
import pt.iscte.datamodel.visitor.Visitor
import pt.iscte.graphicinterface.plugins.Action
import pt.iscte.graphicinterface.plugins.TreeSetup
import pt.iscte.graphicinterface.tree.JSONTreeVisitor
import pt.iscte.injector.Inject
import pt.iscte.injector.InjectAdd
import pt.iscte.injector.Injector
import pt.iscte.instantiation.*
import pt.iscte.utils.GraphicInterfaceUtils.Companion.accept
import pt.iscte.utils.GraphicInterfaceUtils.Companion.expandAll
import pt.iscte.utils.JSONUtils.Companion.serializeWithIndentation


/**
 * Main
 *
 * To initialize the Window and pass the root JSON object to the tree.
 *
 */
fun main(){
    data class User(val name: String, val age: Int, val male: Boolean, val car: String?)
    data class TestObject(val name: String, val age: Int, val mutableList: MutableList<String>,
                          val mutableList2: MutableList<List<String?>>, val user: User)
    val test = TestObject("Love", 2, mutableListOf("Edi", "Iara", "Lina", "Rui"),
        mutableListOf(listOf("A", "B", "C"), listOf("!", "#", "$", null)),
        User("Joca", 15, true, null)).toJSONObject()
    val window = Injector.create(Window::class)
    window.open(test)
}

/**
 * Window
 *
 * @constructor Create empty Window
 */
class Window {
    /**
     * Shell
     *
     * This represents the main window.
     *
     */
    private val shell: Shell = Shell(Display.getDefault())

    /**
     * Tree
     *
     * Tree object will hold in the left side of the shell the JSON divided in tree items.
     *
     */
    private val tree: Tree = Tree(shell, SWT.SINGLE or SWT.BORDER)

    /**
     * Json view
     *
     * This is the label used in the right side of the shell.
     * This will have the serialized result of the selected item of the tree.
     */
    private val jsonView: Label = Label(shell, SWT.BORDER)

    /**
     * Text input
     *
     * To make searches in the the tree.
     */
    private var textInput: Text = Text(shell, SWT.BORDER)

    /**
     * Button bar
     *
     * Will have all the plugin buttons to launch the actions
     */
    private var buttonBar: Composite = Composite(shell, SWT.BORDER)

    /**
     * Fav icon
     *
     * Is the Application Icon
     */
    private val favIcon = "images/applogo/JSONGenerator-logo.png"

    /**
     * Tree setup
     *
     * This will be an Injected element.
     * You can only add class element per runtime.
     * The class to be injected should be defined in conf/conf.properties for the Window.treeSetup variable.
     *
     */
    @Inject
    private lateinit var treeSetup: TreeSetup

    /**
     * Actions
     *
     * This will be an Injected element.
     * You can add many classes per runtime.
     * The classes to be injected should be defined in conf/conf.properties for the Window.actions variable.
     */
    @InjectAdd
    private val actions = mutableListOf<Action>()

    init {
        shell.text = "JSON Generator"
        shell.layout = GridLayout(2,true)
        shell.image = Image(Display.getDefault(), favIcon)

        tree.layoutData = getTreeGridData()
        tree.addSelectionListener(getTreeSelectionAdapter())

        jsonView.layoutData = getJsonViewGridData()

        textInput.layoutData = getTextInputGridData()
        textInput.addModifyListener(getTextInputModifyListener())

        buttonBar.layoutData = getButtonBarGridData()
    }

    /**
     * Init or refresh json viewer
     * Displays the jsonViewer or refreshes it.
     */
    fun initOrRefreshJsonViewer(){
        val jsonRoot = tree.selection.first().data as JSONValue
        jsonView.text = jsonRoot.serializeWithIndentation()
        jsonView.requestLayout()
    }

    /**
     * Get button bar grid data
     *
     * @return
     */
    private fun getButtonBarGridData(): GridData {
        val buttonBarGridData = GridData()
        buttonBarGridData.horizontalAlignment = GridData.FILL
        buttonBarGridData.grabExcessHorizontalSpace = true
        buttonBar.layout = RowLayout(SWT.HORIZONTAL)
        return buttonBarGridData
    }

    /**
     * Get tree selection adapter
     *
     */
    private fun getTreeSelectionAdapter() = object : SelectionAdapter() {
        override fun widgetSelected(e: SelectionEvent) {
            println("selected: " + tree.selection.first().data)
            println("depth: " + (tree.selection.first().data as JSONValue).depth)
            initOrRefreshJsonViewer()
        }
    }

    /**
     * Get text input modify listener
     *
     */
    private fun getTextInputModifyListener() = object : ModifyListener {
        var text: String = ""

        inner class TreeVisitor(val string: String) : Visitor {
            override fun visit(treeItem: TreeItem): Boolean {
                if ("" != string && treeItem.text.contains(string)) {
                    treeItem.background = Color(RGBA(170, 220, 255, 255))
                } else {
                    treeItem.background = Color(RGBA(255, 255, 255, 0))
                }
                return true
            }
        }

        override fun modifyText(e: ModifyEvent?) {
            text = (e!!.widget as Text).text
            val treeVisitor: TreeVisitor = TreeVisitor(text)
            tree.accept(treeVisitor)
        }
    }

    /**
     * Get text input grid data
     *
     * @return
     */
    private fun getTextInputGridData(): GridData {
        val textInputGridData = GridData()
        textInputGridData.horizontalAlignment = GridData.FILL
        textInputGridData.grabExcessHorizontalSpace = true
        return textInputGridData
    }

    /**
     * Get json view grid data
     *
     * @return
     */
    private fun getJsonViewGridData(): GridData {
        val jsonViewGridData = GridData()
        jsonViewGridData.verticalAlignment = GridData.FILL
        jsonViewGridData.grabExcessVerticalSpace = true
        jsonViewGridData.horizontalAlignment = GridData.FILL
        jsonViewGridData.grabExcessHorizontalSpace = true
        return jsonViewGridData
    }

    /**
     * Get tree grid data
     *
     * @return
     */
    private fun getTreeGridData(): GridData {
        val treeGridData = GridData()
        treeGridData.verticalAlignment = GridData.FILL
        treeGridData.grabExcessVerticalSpace = true
        treeGridData.horizontalAlignment = GridData.FILL
        treeGridData.grabExcessHorizontalSpace = true
        return treeGridData
    }

    /**
     * Open
     *
     * Method that initializes all the elements in the shell.
     *
     * @param jsonRoot
     */
    fun open(jsonRoot: pt.iscte.datamodel.Composite) {
        createTreeItems(jsonRoot)
        tree.expandAll()
        actions.forEach { action ->
            val button = Button(buttonBar, SWT.PUSH)
            button.text = action.name
            button.addListener(SWT.Selection) { action.execute(tree, jsonView, this) }
        }
        shell.pack()
        shell.open()
        val display = Display.getDefault()
        while (!shell.isDisposed) {
            if (!display.readAndDispatch()) display.sleep()
        }
        display.dispose()
    }

    /**
     * Create tree items
     *
     * @param jsonRoot
     */
    private fun createTreeItems(jsonRoot: pt.iscte.datamodel.Composite){
        val jsonTreeVisitor = JSONTreeVisitor(tree, treeSetup)
        jsonRoot.accept(jsonTreeVisitor)
    }
}