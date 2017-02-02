using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class MovPlayer : MonoBehaviour {

    public float velocidadBola;
    private Vector3 dir;
    public bool juego;
    private int record = 0;
    private int puntos;
    public Text texto_puntos;
    public Text texto_scoreActual;
    public Text texto_record;
    public GameObject wii;
    public GameObject ui;
    private AudioSource audS;

	void Start () {
        TileManager.inicial = true;
        dir = new Vector3(0, 0);
        juego = true;
        puntos = 0;
        audS = (AudioSource)this.GetComponent<AudioSource>();
	}
	
	// Update is called once per frame
	void Update () {
        texto_puntos.text = ""+puntos;
        if (Input.GetMouseButtonDown(0) || Input.GetKeyDown(KeyCode.LeftArrow) )
        {
            if (dir == new Vector3(-1, 0))
            {
                dir = new Vector3(0, 0, 1);
            }
            else
            {
                dir = new Vector3(-1, 0);
            }
            if(this.GetComponent<Rigidbody>().isKinematic)
            {
                this.GetComponent<Rigidbody>().isKinematic = false;
            }
        }
        if (!juego)
        {
            ui.SetActive(true);
            if (puntos > record)
                record = puntos;

            texto_puntos.text = "";
            texto_scoreActual.text = "" + puntos;
            texto_record.text = "" + record;

            //Destroy(this.gameObject);
        }

    /*    if (Input.GetMouseButtonDown(1) || Input.GetKeyDown(KeyCode.RightArrow))
        {
            if (dir == new Vector3(1, 0))
            {
                dir = new Vector3(0, 0, 1);
            }
            else
            {
                dir = new Vector3(1, 0);
            }
        }
    */
        this.transform.position += velocidadBola * dir * Time.deltaTime;

        if(this.transform.position.y < -3f)
        {
            juego = false;
        }

	}

    void OnTriggerEnter(Collider other)
    {
        Debug.Log(other.tag);
        if(other.tag == "diamante")
        {
            puntos += 30;
            Destroy(other.gameObject);
            Instantiate(wii, transform.position,Quaternion.identity);
            audS.Play();
        }
        else
        {
            if (other.tag == "tile")
            {
                puntos++;
            }
        }
    }

    public void Restart()
    {
        this.GetComponent<Rigidbody>().isKinematic = true;
        this.transform.position = new Vector3(0, 0, 0);
        TileManager.inicial = true;
        dir = new Vector3(0, 0);
        juego = true;
        puntos = 0;
        ui.SetActive(false);
    }

    public void Salir()
    {
        Application.Quit();
    }
    
}
