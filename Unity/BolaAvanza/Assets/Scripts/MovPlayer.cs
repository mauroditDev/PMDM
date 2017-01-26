using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class MovPlayer : MonoBehaviour {

    public float velocidadBola;
    private Vector3 dir;
    public bool juego;
    private int puntos;
    public Text texto;
    public GameObject wii;

	void Start () {
        TileManager.inicial = true;
        dir = new Vector3(0, 0);
        juego = true;
        puntos = 0;
        
	}
	
	// Update is called once per frame
	void Update () {
        texto.text = ""+puntos;
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
        }
        else
        {
            if (other.tag == "tile")
            {
                puntos++;
            }
        }
    }
    
}
