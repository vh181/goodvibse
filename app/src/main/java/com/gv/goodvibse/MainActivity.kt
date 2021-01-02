package com.gv.goodvibse

//import androidx.databinding.DataBindingUtil
import android.content.ActivityNotFoundException

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleObserver
import com.example.android.dessertpusher.DessertTimer
import com.gv.goodvibse.databinding.ActivityMainBinding


import timber.log.Timber

const val  KEY_REVENUE= "Key_revenue"
const val  DESSERTS_SOLD="desserts_Sold"
const val  MENSSAGEM = "Menssa_gem"
var menmen = " "
class MainActivity : AppCompatActivity(), LifecycleObserver {

    private var revenue = 0
    private var dessertsSold = 0
    private var mensage = "Ola , eu sou a Pipoca e vou te animar :D "
    private var funde =R.mipmap.lua2
    var  clique: String = "Clique em mim para Começar e Continue Cliquando Au Au :+ "
    private  lateinit var  dessertTimer: DessertTimer
    // Contains all the views

private  lateinit var binding : ActivityMainBinding


    data class Doguinho(val imageId: Int, val price: Int, val startProductionAmount: Int,val fund: Int , val mensagem : String)

    // Create a list of all desserts, in order of when they start being produced
    private val allDesserts = listOf(
        Doguinho(R.mipmap.pipoca59, 5, 0,R.mipmap.lua2 ,"A vida as vezes pode ser dura  que  nem no Naruto mas vai por mim, o lance não é controla-la mas sim  adapta-la :D "),
        Doguinho(R.mipmap.pipoca14, 10, 5,R.drawable.campo,"Relaxa confia na pipoca , no final sempre dá bom :+"),
        Doguinho(R.mipmap.pipoca85, 10, 10,R.mipmap.lote,"Procrastinar  faz parte , tudo bem ter um dia que a gente nao consiga produzir nada , nao precisa se sentir mal"),
        Doguinho(R.mipmap.pipoca21, 15, 15,R.drawable.praia," O ideial é sempre buscar o equilibrio na vida , mas igual a minha carinha as vezes relaxa é a melhor opção :3"),
        Doguinho(R.mipmap.pipoca86, 10, 20,R.mipmap.aurora,"O importante é sempre tentar, então parabéns você tentou você é incrível"),
        Doguinho(R.mipmap.pipoca22, 20, 25,R.drawable.roda,"Aproveite o presente , viva-o , é uma dádiva, mas sempre pensado em seu futuro :)"),
        Doguinho(R.mipmap.pipoca90, 10, 30,R.drawable.barco,"Faça a difereça no mundo , continue sendo estranho rs :)"),
        Doguinho(R.mipmap.pipoca56, 50, 35,R.mipmap.paris4,"Eu sei mudar é complicado , é dificil, mas se não tentármos não saberemos como seria se fossemos diferente "),
        Doguinho(R.mipmap.pipoca87, 10, 40,R.mipmap.px,"As vezes eu fico olhando as estrelas , me sinto uma cachorra exploradora e você ? "),
        Doguinho(R.mipmap.pipoca93, 10, 45,R.mipmap.cb,"BEBA AGUA HUM U.U , melhor ter pedra no caminho do que nos rins vai por mim , sou uma doguinha vivida :D"),
        Doguinho(R.mipmap.pipoca51, 100, 50,R.mipmap.cachoeira,"As vezes erramos a vida é assim mas podemos aprender com nossos erros ou apenas continuar errando "),
        Doguinho(R.mipmap.pipoca88, 10, 55,R.mipmap.kk,"Eu sei que pesa a saudades daqueles que já se foram mas é bom aproveitar aqueles que ficaram tambem :) "),
        Doguinho(R.mipmap.pipoca53, 150, 60,R.mipmap.gg,"Ao olhar da Pipoca ser Adulto não é uma virada de chave , ser adulto é uma criança que amadureceu porque teve que amadurecer ou uma criança que ainda é uma criança só que com responsabilidades "),
        Doguinho(R.mipmap.pipoca89, 10, 65,R.mipmap.qq,"As vezes ficou doida de tanto que eu pulo  haha"),
        Doguinho(R.mipmap.pipoca55, 200, 70,R.mipmap.gatoss,"Sempre que puder olhe as estrelas , você estará conectado com o passado e o futuro"),

        Doguinho(R.mipmap.pipoca57, 250, 75,R.mipmap.natal,"Saudades de alguem importante olhe para a lua , se essa pessoa tambem sentir ela tambem irá olhar"),
        Doguinho(R.mipmap.pipoca62, 270, 80,R.mipmap.estrada,"Curiosidade  da Caopanheira , Os Franceses dizem que ver a Lua no dia é sinal de boa sorte , caçem rs"),
        Doguinho(R.mipmap.pipoca13, 300, 85,R.mipmap.trilhja,"Tem dias que levantar as patinhas da cama já é uma grande vitória !!!"),
        Doguinho(R.mipmap.pipoca60, 320, 90,R.mipmap.gelo,"Sinceramente , Naruto ou Dragonball ? eu prefiro com certeza uma carninha hahaaha"),
        Doguinho(R.mipmap.pipoca10, 350, 95,R.drawable.uni,"O tempo passa tão rapido eu vejo como as pessoas são estranhas , e sabe o que é louco ? Está tudo bem , seja estranho tambem você é especial e pessoas te amam pela sua estranheza :D"),
        Doguinho(R.mipmap.pipoca61, 370, 100,R.mipmap.ff,"O meu Dono me abusou de Modelo nesse App ...."),
        Doguinho(R.mipmap.pipoca94, 10, 105,R.mipmap.ss,"Mas eu amo ele , e você ama alguém também ?"),
        Doguinho(R.mipmap.pipoca52, 400, 110,R.mipmap.irlanda,"O amor é algo louco mas sincero então ,em tempos de tanta maldade escolha sempre a opção que nao pese no seu sono a noite"),
        Doguinho(R.mipmap.pipoca63, 420, 115,R.mipmap.ahua,"Toda vida importa , toda história tambem , já que todos somos universos a procura de conforto e um bem :3"),

        Doguinho(R.mipmap.pipoca65, 450, 120,R.mipmap.lossa,"Não entendo essa coisa do meu humano de ficar enrolando de fazer as coisas , vc também enrola ? faz assim tenta fazer em pequenas etapas , você consegue ! , você é incrivel :D"),
        Doguinho(R.mipmap.pipoca66, 470, 125,R.mipmap.fres,"Humano , humano , foca dieta não pode , deixa que eu como pra você haha"),
        Doguinho(R.mipmap.pipoca58, 500, 130,R.mipmap.fsaw,"Festa , festa , festa , ta mas porque meus humanos estão aqui todo dia ? para mim é festa todo dia  , eu ouvir dizer que é um tal de covid... "),
        Doguinho(R.mipmap.pipoca91, 10, 135,R.drawable.campo,"Acho que eles vão passar bastante tempo aqui , melhor eu ficar perto deles"),
        Doguinho(R.mipmap.pipoca8, 550, 140,R.mipmap.palneta,"Hoje minha humana estava meio malzinha , acho que ela falou algo sobre quarentena , mas ela é forte ! E VOCÊ TAMBEM HUMANO :)"),
        Doguinho(R.mipmap.pipoca24, 570, 145,R.mipmap.luz,"As vezes eu sei parece que as coisas estão complicadas e ruins mas existem situações e coisas boas rolando no mundo é só a gente olhar melhor"),
        Doguinho(R.mipmap.pipoca92, 10, 150,R.mipmap.rr,"Viver é doido as vezes você ta correndo no parque e outras vezes chorando e uivando tristinha , ESQUILO!"),
        Doguinho(R.mipmap.pipoca12, 600, 155,R.mipmap.espaco,"Mudar faz bem , mas devemos tomar cuidado nem toda mudança é pra melhor"),
        Doguinho(R.mipmap.pipoca11, 650, 160,R.mipmap.tango,"Cuide bem de seus amiguinhos que nem eu porque nós podemos ser grandes amigos em momentos de apertos :3"),

        Doguinho(R.mipmap.pipoca9, 670, 165,R.mipmap.banco,"Quando se quer pode-se resolver uma situação que pra maioria não há saida"),
        Doguinho(R.mipmap.pipoca54, 690, 170,R.mipmap.cabana,"Desistir jamais!"),

        Doguinho(R.mipmap.pipoca7, 730, 175,R.mipmap.hobbit,"É eu sei eu sou linda , pode fazer um carinho em mim haha Vai clica em mim haha"),
        Doguinho(R.mipmap.pipoca64, 760, 180,R.mipmap.predios,"A vida é imprevisivel então tente seu melhor e veja o que acontece :+"),
        Doguinho(R.mipmap.pipoca95, 10, 185,R.mipmap.jj,"Você nao pode mudar uma outra pessoa mas pode se mudar :)"),
        Doguinho(R.mipmap.pipoca96, 10, 190,R.mipmap.ee,"Quando quer o ser humano é incrível mas quando também quer é assustador"),
        Doguinho(R.mipmap.pipoca97, 10, 195,R.mipmap.cha,"Há beleza em tudo e em todos o que nos falta para entedermos isso é apenas olhar com calma rs :+"),
        Doguinho(R.mipmap.pipoca99, 10, 200,R.mipmap.nn,"Viva do seu modo e econtre seu norte , sempre é claro sem fazer mal aos amiguinhos!"),
        Doguinho(R.mipmap.pipoca98, 10, 205,R.mipmap.bb,"Viver ou Comer eis a questão ! :3 "),
        Doguinho(R.mipmap.pipoca6, 780, 210,R.mipmap.ffsdfsd,"Ta acabando , eu gostaria de agradecer pela caminhada e mensagens comigo :3 , a próxima é a  ultima :("),
        Doguinho(R.mipmap.pipoca5,800,215, R.mipmap.fin,"Seja você , você é incrivel e sendo assim irá construir uma vida que fará sentido no final mesmo que seja confusa na construção hahah bye au au")
    )


    private var currentDessert = allDesserts[0]


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dessertsSold= 0

        // Use Data Binding to get reference to the views
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.dessertButton.setOnClickListener {
            onDessertClicked()
        }
        dessertTimer = DessertTimer(this.lifecycle)
        // Set the TextViews to the right values

        if (savedInstanceState !=null){
            revenue = savedInstanceState.getInt(KEY_REVENUE)
            dessertsSold = savedInstanceState.getInt(DESSERTS_SOLD)
            mensage= savedInstanceState.getString(MENSSAGEM).toString()


        }

        binding.cliques.text = clique
        binding.revenue = revenue
        binding.amountSold = dessertsSold
    //    binding.mensa=mensage
      //  mensage= currentDessert.mensagem
        // Make sure the correct dessert is showing
        binding.dessertButton.setImageResource(currentDessert.imageId)
        binding.backgroundImage.setImageResource(R.mipmap.adasdas)
        //     binding.dessertButton.setImageResource(currentDessert.fund)
    }

    /**
     * Updates the score when the dessert is clicked. Possibly shows a new dessert.
     */
    private fun onDessertClicked() {
        binding.backgroundImage.setImageResource(currentDessert.fund)
        binding.mensa=currentDessert.mensagem
        // Update the score
        revenue += currentDessert.price
        dessertsSold++
        menmen=currentDessert.mensagem
        binding.revenue = revenue
        binding.amountSold = dessertsSold

        clique=" "
        binding.cliques.text = clique




        // Show the next dessert
        showCurrentDessert()
    }

    /**
     * Determine which dessert to show.
     */
    private fun showCurrentDessert() {
        var newDessert = allDesserts[0]
        for (dessert in allDesserts) {
            if (dessertsSold >= dessert.startProductionAmount  ) {
                newDessert = dessert
            }

            else break
        }

        // If the new dessert is actually different than the current dessert, update the image
        if (newDessert != currentDessert) {
            currentDessert = newDessert
           binding.dessertButton.setImageResource(newDessert.imageId)
            binding.backgroundImage.setImageResource(currentDessert.fund)
            binding.mensa=currentDessert.mensagem
            menmen=currentDessert.mensagem


        }
    }

    /**
     * Menu methods
     */
    private fun onShare() {
        val shareIntent = ShareCompat.IntentBuilder.from(this)
            .setText(getString(R.string.share_text, dessertsSold, revenue,menmen))
            .setType("text/plain")
            .intent
        try {
            startActivity(shareIntent)
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(this, getString(R.string.sharing_not_available),
                Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.shareMenuButton -> onShare()
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt( KEY_REVENUE,revenue)
        outState.putInt(DESSERTS_SOLD,dessertsSold)
        outState.putString(MENSSAGEM,mensage)
    }

    override fun onStart() {
        super.onStart()
        Timber.i("onStart Called")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume Called")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("onPause Called")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy Called")
    }


}


