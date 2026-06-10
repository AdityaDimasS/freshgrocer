<?php

use PHPUnit\Framework\TestCase;

class IndexTest extends TestCase
{
    public function testIndexPageLoads()
    {
        $_SERVER['REQUEST_METHOD'] = 'GET';

        ob_start();
        include __DIR__ . '/../index.php';
        $output = ob_get_clean();

        $this->assertStringContainsString(
            'FreshGrocer Order Form',
            $output
        );
    }

    public function testSuccessfulOrderDisplay()
    {
        $_SERVER['REQUEST_METHOD'] = 'POST';

        $_POST = [
            'userId' => 'U001',
            'productId' => 'P001',
            'tier' => 'GOLD',
            'quantity' => '5',
            'duration' => '6',
            'address' => 'Ponorogo'
        ];

        ob_start();
        include __DIR__ . '/../index.php';
        $output = ob_get_clean();

        $this->assertStringContainsString(
            'FreshGrocer Order Form',
            $output
        );
    }
}